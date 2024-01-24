
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNGetVideofilesSpec.h"

@interface GetVideofiles : NSObject <NativeGetVideofilesSpec>
#else
#import <React/RCTBridgeModule.h>

@interface GetVideofiles : NSObject <RCTBridgeModule>
#endif

@end
