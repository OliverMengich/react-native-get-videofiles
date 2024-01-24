# react-native-get-videofiles

A module in react native for reading video files for android devices

``*important*``: Currently only suppoerted for android devices. If you want support for IOS or windows feel free to contribute to the repository.
## Installation

```sh
npm install react-native-get-videofiles
```
## Allow Permissions
go to the ``android/app/src/main/AndroidManifest.xml`` then append the following permmmision
```xml
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
```

## Usage

```js
import { getAll } from 'react-native-get-videofiles';

// ...

const result = await getAll({})
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
