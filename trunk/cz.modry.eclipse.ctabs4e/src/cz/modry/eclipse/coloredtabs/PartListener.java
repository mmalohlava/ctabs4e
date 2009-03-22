/**
 * 
 */
package cz.modry.eclipse.coloredtabs;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchThemeConstants;
import org.eclipse.ui.themes.ITheme;

import cz.modry.eclipse.coloredtabs.properties.ColoredTabsPropertyPage;

/**
 * @author Michal Malohlava
 * 
 * TODO theme listener which will reflect theme changes
 *
 */
public class PartListener implements IPartListener2 {
	
	private RGB originalThemeColor;
		
	@SuppressWarnings("restriction")
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(true);
		
		if (part instanceof IEditorPart) {
			IEditorInput editorInput = ((IEditorPart) part).getEditorInput();
			IResource res = (IResource) editorInput.getAdapter(IResource.class);
			if (res != null) {
				RGB rgb = ColoredTabsPropertyPage.getProjectColor(res.getProject());
				
				ITheme theme = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme();
				ColorRegistry colorRegistry = theme.getColorRegistry();
				
				originalThemeColor = colorRegistry.getRGB(IWorkbenchThemeConstants.ACTIVE_TAB_BG_START);
				colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_BG_START, rgb);
			}
		}
	}

	
	@SuppressWarnings("restriction")
	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// restore original color
		if (originalThemeColor != null) {
			if (partRef instanceof IEditorReference) {
				ITheme theme = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme();
				ColorRegistry colorRegistry = theme.getColorRegistry();
				
				colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_BG_START, originalThemeColor);
			}
		}	
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {		
		
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
	}

	
}
