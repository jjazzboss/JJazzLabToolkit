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

import java.awt.Dialog;
import java.util.logging.Logger;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.lookup.ServiceProvider;

/**
 * Basic implementation which just print messages on stdout.
 */
@ServiceProvider(service = DialogDisplayer.class)
public class ToolkitDialogDisplayer extends DialogDisplayer
{

    static Logger getLogger()
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
        return Logger.getLogger(ToolkitDialogDisplayer.class.getSimpleName());
    }
    private static final Logger LOGGER = getLogger();

    public ToolkitDialogDisplayer()
    {
        LOGGER.info("Using ToolkitDialogDisplayer()");
    }

    @Override
    public Object notify(NotifyDescriptor nd)
    {
        String msg = nd.getMessage().toString();
        switch (nd.getMessageType())
        {
            case NotifyDescriptor.WARNING_MESSAGE ->
                LOGGER.warning(msg);
            case NotifyDescriptor.ERROR_MESSAGE ->
                LOGGER.severe(msg);
            case NotifyDescriptor.QUESTION_MESSAGE ->
                throw new IllegalArgumentException("nd=" + nd);
            default ->
                LOGGER.info(msg);
        }

        return NotifyDescriptor.CLOSED_OPTION;
    }

    @Override
    public Dialog createDialog(DialogDescriptor dd)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
