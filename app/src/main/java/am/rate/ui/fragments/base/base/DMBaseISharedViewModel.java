package am.rate.ui.fragments.base.base;

import androidx.lifecycle.LifecycleOwner;

interface DMBaseISharedViewModel {

    <SharedData> void sendSharedData(final int sendCode, final SharedData data);

    <SharedData> void getSharedData(final LifecycleOwner owner, final int sendCode, final DMBaseIOnSharedDataListener<SharedData> listener);

    <SharedData> void getSharedDataAlways(final LifecycleOwner owner, final int sendCode, final DMBaseIOnSharedDataListener<SharedData> listener);
}
