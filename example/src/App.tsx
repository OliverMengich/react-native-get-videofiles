import * as React from 'react';

import { StyleSheet, View, FlatList, Text } from 'react-native';
import RNVideoModule from 'react-native-get-videofiles';
import type { VideoFile } from 'react-native-get-videofiles';
export default function App() {;
  const [videos,setVideos] = React.useState<VideoFile[]>([]);
  React.useEffect(() => {
    RNVideoModule.getAll({limit:4}).then(setVideos)
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={videos}
        renderItem={({item})=>(
          <View>
            <Text>{item.displayName}</Text>
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
