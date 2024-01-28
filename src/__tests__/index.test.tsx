import RNGetVideofiles from "react-native-get-videofiles";
jest.mock('react-native', () => {
    const RNGetVideofiles = {
        getAll: jest.fn(),
    };
    return {
        Platform: {
            OS: 'android',
            select: jest.fn(),
        },
        NativeModules: {
            RNGetVideofiles,
        },
    };
});

it('Gets all videos', async () => {
    const videos = await RNGetVideofiles.getAll({
        limit: 10,
    });
    expect(videos).toMatchSnapshot();
});