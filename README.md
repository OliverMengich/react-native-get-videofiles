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
import RNVideoFiles from 'react-native-get-videofiles';
import type {VideoFile} from 'react-native-get-videofiles';
import {useState} from 'react';
import {Flatlist,View,Text} from 'react-native';
// ...

const App = ()=>{
    const [videos,setVideos] = useState<VideoFile[]>([]);
    useEffect(()=>{
        const fc=async ()=>{
            const result = await RNVideoFiles.getAll({limit:1000});
            setVideos(result);
        }
        fc();
    },[])
    return(
        <View style={{flex:1}}>
            <Flatlist
                data={videos}
                renderItem={({item})=>(
                    <View>
                        <Text>{item.displayName}</Text>
                    </View>
                )}
            />
        </View>
    )
}
export default App;
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
