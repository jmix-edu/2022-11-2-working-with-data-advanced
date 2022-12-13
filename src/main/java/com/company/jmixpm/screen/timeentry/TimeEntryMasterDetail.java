package com.company.jmixpm.screen.timeentry;

import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.TimeEntry;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("TimeEntryMasterDetail")
@UiDescriptor("time-entry-masterdetail.xml")
@LookupComponent("table")
public class TimeEntryMasterDetail extends MasterDetailScreen<TimeEntry> {
    @Autowired
    private SplitPanel split;
    @Autowired
    private VBoxLayout editBox;

    @Override
    protected void disableEditControls() {
        super.disableEditControls();

        setEditPanelVisibility(false);
    }

    @Override
    protected void enableEditControls(boolean creating) {
        super.enableEditControls(creating);

        setEditPanelVisibility(true);
    }

    private void setEditPanelVisibility(boolean visibility) {
        editBox.setVisible(visibility);
        split.setSplitPosition(visibility ? 50 : 100, SizeUnit.PERCENTAGE);
    }
}