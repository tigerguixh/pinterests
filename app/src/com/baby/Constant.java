package com.baby;

import android.widget.Toast;

public class Constant {

    /*
     * App Version info
     */
    public static final int SHARED_PREFERENCES_VERSION = 1;

    /*
     * Configuration values
     */
    public static final boolean ENABLE_CRASH_TRACKING = true;
    public static final int CACHE_VERSION = 4;
    public static final boolean ENABLE_STATUS_CACHING = true;
    public static final boolean UPDATE_CACHED_STATUSES = true;

    public static final String DIR_NAME_PROFILE_IMAGES = "profile_images";
    public static final String DIR_NAME_CACHED_THUMBNAILS = "cached_thumbnails";

    /*
     * App Constants
     */
    public static final int REFRESH_LISTS_WAIT_TIME = 1000 * 30;
    public static final int DEFAULT_TOAST_DISPLAY_TIME = Toast.LENGTH_LONG;
    public static final int RESTORE_SAVED_DRAFT_SECONDS = 60 * 5;

    /*
     *
	 */
    public static final long USER_ID_CHRISMLACY = 195348872;
    public static final long USER_ID_TWEETLANES = 482651243;

    // Note:
    // User == the person who signed in.
    // Profile == any other account on Twitter
    public enum LaneType {
        DIRECT_MESSAGES, USER_PROFILE, USER_PROFILE_TIMELINE, USER_HOME_TIMELINE, USER_LIST_TIMELINE, USER_MENTIONS, USER_FAVORITES, RETWEETS_OF_ME, PROFILE_PROFILE, // ahem
        PROFILE_PROFILE_TIMELINE, // yes, i know
        PROFILE_MENTIONS, PROFILE_FAVORITES, FRIENDS, FOLLOWERS, SEARCH_TERM, SEARCH_PERSON, STATUS_SPOTLIGHT, STATUS_CONVERSATION, STATUS_RETWEETED_BY, GLOBAL_FEED, MAX,
    }

    /*
     *
	 */
    public enum SystemEvent {
        VOLUME_UP_KEY_DOWN, VOLUME_DOWN_KEY_DOWN, FORCE_FRAGMENT_PAGER_ADAPTER_REFRESH, RESTART_APP, DISPLAY_TOAST,
    }

    /*
	 *
	 */
    public static final int REQUEST_CODE_IMAGE_PICKER = 12345;
    public static final int REQUEST_CODE_CAMERA = 12346;
    public static final int REQUEST_CODE_SPOTLIGHT = 12347;
    public static final int REQUEST_CODE_PROFILE = 12348;


}
