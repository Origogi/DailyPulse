//
//  AboutView.swift
//  iosApp
//
//  Created by 김정태 on 10/2/25.
//

import ComposeApp
import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Devices")
        }
    }
}

private struct AboutListView: View {
    var body: some View {
        List {
            InfoRow(label: "OS", value: "\(Platform.shared.osName) \(Platform.shared.osVersion)")
            InfoRow(label: "Device", value: Platform.shared.deviceModel)
            InfoRow(label: "Density", value: "\(Platform.shared.density)x")
        }
        .listStyle(.insetGrouped)
    }
}

struct InfoRow: View {
    let label: String
    let value: String

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(label)
                .font(.caption)
                .foregroundColor(.secondary)
            Text(value)
                .font(.body)
        }
        .padding(.vertical, 4)
    }
}

#Preview {
    AboutScreen()
}
