# Modularization Architecture Example

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

[简体中文版说明 >>>](https://github.com/andyhaha/modules/blob/main/README_cn.md)

## Project Overview

This project demonstrates a modularization architecture based on Kotlin and Android. By using modularization, we can split an app into multiple independent modules, each of which can either be UI-related or non-UI-related, depending on the requirements. Components communicate via Services, achieving decoupling and increasing code reusability.

This architecture supports dynamic loading and unloading of components across different Activities, while also allowing communication between components via Services for flexible management and extension.

## Features

- **UI Modularization**: Each component can be related to UI, such as `TitleBar`, `Content`, and `BottomBar`, or non-UI functionality.
- **Service Communication**: Components communicate via Services, achieving decoupling.
- **Dynamic Loading & Management**: Components can be dynamically loaded, initialized, and destroyed, with management via a unified factory.
- **Activity Lifecycle Management**: Components manage their lifecycle based on the Activity lifecycle to avoid memory leaks and resource wastage.
## Architecture Design

### Core of Modularization Architecture

- **Component Class**: Each component extends the `Component` class, which manages the lifecycle and binds the Activity and View.
- **Factory Class**: The `ComponentFactory` class is used to create and manage all component instances, providing a unified interface for retrieving, registering, and destroying components.
- **Service Manager**: The `ServiceManager` class provides a mechanism for communication between components using Services.
- **MainActivityDelegate**: The `MainActivityDelegate` class manages the creation, lifecycle, and destruction of components via delegation.

## How to Use

1. **Add Modules**: To add a new module, create a new class extending `Component` in the `modules/` directory.
2. **Register Components**: Use the `ComponentFactory` to register and manage your components.
3. **Service Communication**: To enable communication between components, use `ServiceManager` to send and receive messages.
4. **Activity Integration**: Ensure that your components are integrated into the activity lifecycle through `MainActivityDelegate`.

## License

Copyright (c) [2024] [Andy]

Permission is hereby granted, free of charge, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of this software, and to permit others to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT, OR OTHERWISE, ARISING FROM, OUT OF, OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
