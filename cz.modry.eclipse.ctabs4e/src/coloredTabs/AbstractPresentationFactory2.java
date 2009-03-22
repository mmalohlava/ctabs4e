package coloredTabs;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolder;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolderColors;
import org.eclipse.ui.internal.presentations.util.TabFolderEvent;
import org.eclipse.ui.internal.presentations.util.TabFolderListener;
import org.eclipse.ui.internal.presentations.util.TabbedStackPresentation;
import org.eclipse.ui.presentations.AbstractPresentationFactory;
import org.eclipse.ui.presentations.IStackPresentationSite;
import org.eclipse.ui.presentations.StackPresentation;
import org.eclipse.ui.presentations.WorkbenchPresentationFactory;

import sun.security.jca.GetInstance.Instance;

@SuppressWarnings("restriction")
public class AbstractPresentationFactory2 extends WorkbenchPresentationFactory {
	
	TabFolderListener tabFolderListener = new TabFolderListener() {

		@Override
		public void handleEvent(TabFolderEvent e) {
			System.out
					.println("!!!!! TabFolderListener.enclosing_method(): " + e);
			e.tab.setBold(true);
			
		}
		
	};
	
	PaintListener paintListener = new PaintListener() {

		@Override
		public void paintControl(PaintEvent e) {
			System.out
					.println("PaintListener.enclosing_method(): " + e);
			if (e.widget instanceof CTabFolder) {
//				CTabFolder ctf = (CTabFolder) e.widget;
//				
//				ctf.setSelectionBackground(new Color(null, 0, 100, 10));
			}			
			
		}
		
	};

	public AbstractPresentationFactory2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public StackPresentation createEditorPresentation(Composite parent,
			IStackPresentationSite site) {
		System.out
				.println("AbstractPresentationFactory2.createEditorPresentation()");
		StackPresentation sp = super.createEditorPresentation(parent, site);
				
		TabbedStackPresentation tsp = (TabbedStackPresentation) sp;		
		DefaultTabFolder dtf = (DefaultTabFolder) tsp.getTabFolder();
		
		System.err.println("YEAH");
		dtf.addListener(tabFolderListener);		
//		dtf.getControl().addPaintListener(paintListener);
		
		//dtf.getControl().setBackground(new Color(null, 0, 100, 0));
		
						
		return sp;
	}

	@Override
	public StackPresentation createStandaloneViewPresentation(Composite parent,
			IStackPresentationSite site, boolean showTitle) {
		System.out
				.println("AbstractPresentationFactory2.createStandaloneViewPresentation()");
		return super.createStandaloneViewPresentation(parent, site, showTitle);
	}

	@Override
	public StackPresentation createViewPresentation(Composite parent,
			IStackPresentationSite site) {
		
		System.out
				.println("AbstractPresentationFactory2.createViewPresentation()");
		return super.createViewPresentation(parent, site);
	}

}
