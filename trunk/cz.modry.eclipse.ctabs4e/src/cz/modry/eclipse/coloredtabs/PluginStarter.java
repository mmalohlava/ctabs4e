/**
 * 
 */
package cz.modry.eclipse.coloredtabs;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Michal Malohlava
 *
 */
public class PluginStarter implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
//		System.err.println("PluginStarter.earlyStartup()");
//		WindowListener wl = new WindowListener();
//		
//		PlatformUI.getWorkbench().addWindowListener(wl);
//		
//		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows(); 
//		
//		for (int i = 0; i < windows.length; i++) {			
//			if (windows[i] != null) {
//				windows[i].addPageListener(wl.getPageListener());				
//				if (windows[i].getActivePage() != null) {
//					windows[i].getActivePage().addPartListener(wl.getPageListener().getPartListener());
//				}
//			}
//		}
	}

}
