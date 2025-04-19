// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AalzehlaCapacitorCropper",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "AalzehlaCapacitorCropper",
            targets: ["CapacitorCropperPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "CapacitorCropperPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/CapacitorCropperPlugin"),
        .testTarget(
            name: "CapacitorCropperPluginTests",
            dependencies: ["CapacitorCropperPlugin"],
            path: "ios/Tests/CapacitorCropperPluginTests")
    ]
)
