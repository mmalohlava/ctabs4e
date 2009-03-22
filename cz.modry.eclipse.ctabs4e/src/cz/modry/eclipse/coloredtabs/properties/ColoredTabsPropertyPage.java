package cz.modry.eclipse.coloredtabs.properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;

import cz.modry.eclipse.coloredtabs.PluginActivator;
import cz.modry.eclipse.coloredtabs.decorators.ProjectColorDecorator;

/**
 * 
 * ColoredTabs project properties page.
 * 
 * @author Michal Malohlava
 * 
 * FIXME utilize {@link StringConverter} to save RGB
 *
 */
public class ColoredTabsPropertyPage extends PropertyPage {

	private static final String COLOR_TITLE = "Project color: ";
	private static final RGB DEFAULT_COLOR = new RGB(255, 255, 255);
	private static final String PROJECT_COLOR_PROPERTY = PluginActivator.PLUGIN_ID + "projectColor";
	
	private ColorSelector projectColorSelector;

	/**
	 * Constructor for ColoredTabsPropertyPage.
	 */
	public ColoredTabsPropertyPage() {
		super();
	}

	private void addFirstSection(Composite parent) {
		Composite composite = createDefaultComposite(parent);

		//Label for color field
		Label pathLabel = new Label(composite, SWT.NONE);
		pathLabel.setText(COLOR_TITLE);
		
		// Color selector
		projectColorSelector = new ColorSelector(composite);		
		projectColorSelector.setColorValue(getSavedProjectColor());
	}
	
	private RGB getSavedProjectColor() {
		RGB result = DEFAULT_COLOR;
		
		try {
			String rgbString = getResource().getPersistentProperty(new QualifiedName("", PROJECT_COLOR_PROPERTY));
			
			result = StringConverter.asRGB(rgbString);		
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		addFirstSection(composite);
		
		return composite;
	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
	}

	protected void performDefaults() {
		projectColorSelector.setColorValue(DEFAULT_COLOR);
	}
	
	protected IResource getResource() {
		return (IResource) (getElement().getAdapter(IResource.class));
	}
	
	public boolean performOk() {
		try {
			getResource().setPersistentProperty(new QualifiedName("", PROJECT_COLOR_PROPERTY), 
												StringConverter.asString(projectColorSelector.getColorValue()) 
												);
		} catch (CoreException e) {
			return false;
		}
		
		// fire events to update decorator
		PlatformUI.getWorkbench().getDecoratorManager().update(ProjectColorDecorator.ID);
		
		return true;
	}
	
	
	public static RGB getProjectColor(Object o) {
		RGB result = DEFAULT_COLOR;
		try {
			if (o instanceof IAdaptable) {
				IResource res = (IResource) ((IAdaptable) o).getAdapter(IResource.class);
				
				result = StringConverter.asRGB(res.getPersistentProperty(new QualifiedName("", PROJECT_COLOR_PROPERTY)));
			} 
		} catch (Exception e) {}
		
		return result;
	}

}