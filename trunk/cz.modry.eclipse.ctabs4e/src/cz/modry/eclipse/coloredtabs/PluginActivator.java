package cz.modry.eclipse.coloredtabs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Michal Malohlava
 */
public class PluginActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "cz.modry.eclipse.coloredtabs.ColoredTabs";

	// The shared instance
	private static PluginActivator plugin;
	
	// part listener
	private PartListener partListener;
	
	/**
	 * The constructor
	 */
	public PluginActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;		
		
		registerListeners();
	}
	
	protected void registerListeners() {
		WindowListener wl = new WindowListener();
		
		getWorkbench().addWindowListener(wl);
		
		IWorkbenchWindow[] windows = getWorkbench().getWorkbenchWindows(); 
		
		for (int i = 0; i < windows.length; i++) {			
			if (windows[i] != null) {
				//windows[i].addPageListener(wl.getPageListener());
				windows[i].getPartService().addPartListener(wl.getPageListener().getPartListener());
				
//				if (windows[i].getActivePage() != null) {
//					windows[i].getActivePage().addPartListener(wl.getPageListener().getPartListener());
//				}
			}
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static PluginActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
