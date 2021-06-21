package gui.marker.view;

import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;

public class AssessmentMarkerClassNameField extends MarkerField {

	@Override
	public String getValue(MarkerItem item) {
		return item.getAttributeValue("className", "");
	}

}