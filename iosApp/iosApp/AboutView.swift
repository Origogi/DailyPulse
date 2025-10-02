//
//  AboutView.swift
//  iosApp
//
//  Created by 김정태 on 10/2/25.
//

import SwiftUI
import ComposeApp

struct AboutView: View {

    var body: some View {
        VStack(spacing: 20) {
            Text("About")
                .font(.largeTitle)
                .padding()

            VStack(alignment: .leading, spacing: 12) {
                InfoRow(label: "OS", value: "\(Platform.shared.osName) \(Platform.shared.osVersion)")
                InfoRow(label: "Device", value: Platform.shared.deviceModel)
                InfoRow(label: "Density", value: "\(Platform.shared.density)x")
            }
            .padding()

            Spacer()
        }
        .padding()
    }
}

struct InfoRow: View {
    let label: String
    let value: String

    var body: some View {
        HStack {
            Text("\(label):")
                .fontWeight(.semibold)
            Text(value)
                .foregroundColor(.secondary)
            Spacer()
        }
    }
}
