# Issue1178-Reproducer

This is a reproducer for [eclipse.platform.swt#1178](https://github.com/eclipse-platform/eclipse.platform.swt/issues/1178)
that reproduces the issue happening on macOS Sonoma.

Simply import the projects into Eclipse RCP, each project contains its own *dev.target* platform
that must be activated before the tests can be run. If not installed, install SWTBot / RedDeer in
your Eclipse RCP installation
