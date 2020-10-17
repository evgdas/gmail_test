package utils.helpers;

public class EnvironmentHelper {
    public final static String
            browser = System.getProperty("browser", "chrome"),
            version = System.getProperty("version"),
            remoteDriverUrl = System.getProperty("remote_driver_url", "http://localhost:4444/wd/hub/"),
            videoStorageUrl = System.getProperty("video_storage_url","http://localhost:4444/video/");
    public static final boolean
            isVideoOn = videoStorageUrl != null,
            isRemoteDriver = remoteDriverUrl != null;
}
