/*
 * Copyright 2016.
 * This code is part of Buildup
 */

package buildup.ui;

/**
 * UI component (activity or fragment) that supports refresh operations
 */
public interface Refreshable {

    /**
     * Refresh this view
     */
    void refresh();
}
