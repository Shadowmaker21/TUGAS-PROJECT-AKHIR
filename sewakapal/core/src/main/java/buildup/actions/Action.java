package buildup.actions;


import android.content.Context;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;

public interface Action {

    void execute(@NonNull Context context);

    boolean canDoExecute();

    @NonNull
    AnalyticsInfo getAnalyticsInfo();

}
