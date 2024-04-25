/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 *  Copyright @2019 Jerome Lelasseux. All rights reserved.
 *
 *  This file is part of the JJazzLab software.
 *   
 *  JJazzLab is free software: you can redistribute it and/or modify
 *  it under the terms of the Lesser GNU General Public License (LGPLv3) 
 *  as published by the Free Software Foundation, either version 3 of the License, 
 *  or (at your option) any later version.
 *
 *  JJazzLab is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with JJazzLab.  If not, see <https://www.gnu.org/licenses/>
 * 
 *  Contributor(s): 
 */
package org.jjazz.toolkit;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;

/**
 * Basic implementation which just print the status message on stdout.
 */
@ServiceProvider(service = StatusDisplayer.class)
public class ToolkitStatusDisplayer extends StatusDisplayer
{

    private String text;

    private static final Logger LOGGER = Logger.getLogger(ToolkitStatusDisplayer.class.getSimpleName());

    public ToolkitStatusDisplayer()
    {
        LOGGER.info("Using ToolkitStatusDisplayer()");
    }


    @Override
    public String getStatusText()
    {
        return text;
    }

    @Override
    public void setStatusText(String string)
    {
        text = string;
        LOGGER.log(Level.INFO, "Set status text: {0}", text);
    }

    @Override
    public void addChangeListener(ChangeListener cl)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeChangeListener(ChangeListener cl)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
