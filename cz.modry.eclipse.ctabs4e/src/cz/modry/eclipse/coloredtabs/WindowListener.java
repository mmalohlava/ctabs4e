/**
 * 
 */
package cz.modry.eclipse.coloredtabs;

import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @author michal
 *
 */
public class WindowListener implements IWindowListener {
	
	private PageListener pageListener = new PageListener();

	@Override
	public void windowOpened(IWorkbenchWindow window) {
		window.addPageListener(pageListener);
	}

	@Override
	public void windowClosed(IWorkbenchWindow window) {
		window.removePageListener(pageListener);
	}

	@Override
	public void windowDeactivated(IWorkbenchWindow window) {
		
	}

	@Override
	public void windowActivated(IWorkbenchWindow window) {
		window.addPageListener(pageListener);
	}

	public PageListener getPageListener() {
		return pageListener;
	}

	public void setPageListener(PageListener pageListener) {
		this.pageListener = pageListener;
	}

}
