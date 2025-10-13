//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by 김정태 on 10/7/25.
//

import ComposeApp
import SwiftUI

extension ArticlesScreen {
    @MainActor
    class ArticleViewModelWrapper: ObservableObject {
        let articleViewModel: ArticleViewModel

        @Published
        var articleState: ArticleState

        private var closeable: Closeable?

        init() {
            articleViewModel = ArticlesInjector().articlesViewModel
            articleState = articleViewModel.articleState.value as! ArticleState
        }

        func startObserving() {
            closeable = FlowExtensionsKt.watch(articleViewModel.articleState) { [weak self] state in
                self?.articleState = state as! ArticleState
            }
        }

        func stopObserving() {
            closeable?.close()
        }

        deinit {
            closeable?.close()
        }
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel: ArticleViewModelWrapper

    var body: some View {
        VStack(spacing: 0) {
            AppBar()
            contentView
        }
        .onAppear { viewModel.startObserving() }
    }

    @ViewBuilder
    private var contentView: some View {
        if viewModel.articleState.isLoading {
            centeredContent { Loader() }
        } else if let error = viewModel.articleState.error {
            centeredContent { ErrorMessage(message: error) }
        } else if !viewModel.articleState.articles.isEmpty {
            articlesList
        }
    }

    private var articlesList: some View {
        ScrollView {
            LazyVStack(spacing: 10) {
                ForEach(viewModel.articleState.articles, id: \.self) { article in
                    ArticleItemView(article: article)
                }
            }
            .padding(.horizontal)
        }
    }

    private func centeredContent<Content: View>(@ViewBuilder content: () -> Content) -> some View {
        VStack {
            Spacer()
            content()
            Spacer()
        }
    }
}

private struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

private struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

private struct ErrorMessage: View {
    var message: String

    var body: some View {
        Text(message)
            .font(.title)
    }
}

private struct ArticleItemView: View {
    var article: Article
    private let fallbackImageUrl = "https://static.toss.im/png-icons/securities/icn-sec-fill-NAS0DHQ0D-E0.png"

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            GeometryReader { geometry in
                AsyncImage(url: URL(string: article.imageUrl)) { phase in

                    if let image = phase.image {
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: geometry.size.width, height: geometry.size.height)
                            .clipped()
                    } else if phase.error != nil {
                        AsyncImage(url: URL(string: fallbackImageUrl)) { fallbackPhase in
                            if let fallbackImage = fallbackPhase.image {
                                fallbackImage
                                    .resizable()
                                    .scaledToFill()
                                    .frame(width: geometry.size.width, height: geometry.size.height)
                                    .clipped()
                            } else {
                                Color.gray
                                    .frame(width: geometry.size.width, height: geometry.size.height)
                                    .overlay(
                                        Text("Image Load Error")
                                            .foregroundColor(.white)
                                    )
                            }
                        }
                    } else {
                        ProgressView()
                            .frame(width: geometry.size.width, height: geometry.size.height)
                    }
                }
            }
            .aspectRatio(16/9, contentMode: .fit)
            .id(article.imageUrl)

            Text(article.title)
                .font(.title)
                .fontWeight(.bold)

            Text(article.description_)

            Text(article.date)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

#Preview {
    ArticlesScreen(
        viewModel: .init()
    )
}
