/**
 * 
 */
package cz.modry.eclipse.coloredtabs;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author michal
 *
 */
public class PageListener implements IPageListener {
	
	private PartListener partListener = new PartListener(); 

	@Override
	public void pageActivated(IWorkbenchPage page) {		
		page.addPartListener(partListener);			
	}

	@Override
	public void pageClosed(IWorkbenchPage page) {
		page.removePartListener(partListener);		
	}

	@Override
	public void pageOpened(IWorkbenchPage page) {		
		page.addPartListener(partListener);		
	}

	public PartListener getPartListener() {
		return partListener;
	}

	public void setPartListener(PartListener partListener) {
		this.partListener = partListener;
	}
}
