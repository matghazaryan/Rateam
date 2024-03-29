package am.rate.ui.fragments.base.base;

import java.util.List;

/**
 * IBaseOnPermissionSuccessListener is the interface for handle permission request
 */
public interface DMBaseIOnPermissionSuccessListener {

    default void onPermissionsGranted() {

    }

    default void onPermissionsGranted(final int requestCode, final String permission) {

    }

    default void onPermissionsGranted(final int requestCode, final List<String> permissionList) {

    }

    default void onPermissionsDenied() {

    }

    default void onPermissionsDenied(final int requestCode, final String permission) {

    }

    default void onPermissionsDenied(final int requestCode, final List<String> permissionList) {

    }
}
