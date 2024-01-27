import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-get-videofiles' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const GetVideofiles = NativeModules.GetVideofiles
  ? NativeModules.GetVideofiles
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );
interface Options{
    limit:number
}
export interface VideoFile{
    readonly id:number,
    displayName:string,
    duration:number,
    size:number,
    path:string,
    resolution:string,
}
const RNGetVideofiles = {
    async getAll(options:Options):Promise<VideoFile[]>{
        if(Platform.OS ==='android'){
            return await GetVideofiles.getAll(options);
        }
        throw new Error('ios not support');
    }
};

export default RNGetVideofiles;
