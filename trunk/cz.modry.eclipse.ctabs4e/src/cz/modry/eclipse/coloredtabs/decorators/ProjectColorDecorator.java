/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package cz.modry.eclipse.coloredtabs.decorators;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.WorkbenchActivityHelper;

import cz.modry.eclipse.coloredtabs.properties.ColoredTabsPropertyPage;

/**
 * 
 * @author Michal Malohlava
 * 
 * @see ILightweightLabelDecorator
 */
public class ProjectColorDecorator implements ILightweightLabelDecorator {
	
	public static final String ID = ProjectColorDecorator.class.getName();
	
	private Map<Object, Color> allocatedColors = new HashMap<Object, Color>();
			
	@Override
	public void decorate(Object element, IDecoration decoration) {
		RGB projectRGBColor = ColoredTabsPropertyPage.getProjectColor(element);
		Color projectColor;
		
		if (allocatedColors.containsKey(element)) {			
			projectColor = allocatedColors.get(element);
			if (!projectColor.getRGB().equals(projectRGBColor)) {
				projectColor.dispose();
				projectColor = new Color(null, projectRGBColor);
				allocatedColors.put(element, projectColor);
			}			
		} else {
			projectColor = new Color(null, projectRGBColor);
			allocatedColors.put(element, projectColor);
		}		 			
	
		decoration.setBackgroundColor(projectColor);				
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {	
		for (Color c : allocatedColors.values()) {
			c.dispose();
		}
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
	
	
}