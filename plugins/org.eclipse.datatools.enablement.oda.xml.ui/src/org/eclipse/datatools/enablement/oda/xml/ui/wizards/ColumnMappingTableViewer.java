/*******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Table viewer of column mapping
 * 
 * @version $Revision: 1.1 $ $Date: 2006/09/28 10:19:11 $
 */
public final class ColumnMappingTableViewer
{
	private TableViewer viewer;
	private Composite mainControl;
	private Button btnRemove;
	private Button btnUp;
	private Button btnDown;
	private MenuItem itmRemove;
	private MenuItem itmRemoveAll;

	private static HashMap typeIdDisplayNameMapping = new HashMap();
    static
    {
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.TIMESTAMP ),Messages.getString("datatypes.dateTime") );
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.BIGDECIMAL ),Messages.getString("datatypes.decimal") );
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.DOUBLE ),Messages.getString("datatypes.float") );
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.INT ),Messages.getString("datatypes.integer") );
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.DATE ),Messages.getString("datatypes.date"));
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.TIME ),Messages.getString("datatypes.time"));
    	typeIdDisplayNameMapping.put( new Integer( DataTypes.STRING ),Messages.getString("datatypes.string"));
    }
    
	/**
	 * column mapping table viewer. it supplys the button of remove, up , down
	 * and the menu of remove,removeAll....
	 */
	public ColumnMappingTableViewer( Composite parent, boolean showMenus,
			boolean showButtons, boolean enableKeyStrokes )
	{
		mainControl = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		mainControl.setLayout( layout );

		GridData data = null;
		viewer = new TableViewer( mainControl, SWT.FULL_SELECTION );
		data = new GridData( GridData.FILL_BOTH );
		viewer.getControl( ).setLayoutData( data );

		viewer.getTable( ).setHeaderVisible( true );
		viewer.getTable( ).setLinesVisible( true );

		if ( showButtons )
		{
			Composite btnComposite = new Composite( mainControl, SWT.NONE );
			data = new GridData( );
			data.verticalAlignment = SWT.CENTER;
			btnComposite.setLayoutData( data );
			GridLayout btnLayout = new GridLayout( );
			layout.verticalSpacing = 20;
			btnComposite.setLayout( btnLayout );

			GridData btnData = new GridData( GridData.CENTER );
			btnData.widthHint = 20;
			btnData.heightHint = 20;
			btnUp = new Button( btnComposite, SWT.ARROW | SWT.UP );
			btnUp.setLayoutData( btnData );

			btnData = new GridData( GridData.CENTER );
			btnData.widthHint = 20;
			btnData.heightHint = 20;
			btnRemove = new Button( btnComposite, SWT.PUSH );
			btnRemove.setImage( PlatformUI.getWorkbench( )
					.getSharedImages( )
					.getImage( ISharedImages.IMG_TOOL_DELETE ) );
			btnRemove.setLayoutData( btnData );
			btnRemove.addSelectionListener( new SelectionListener( ) {

				public void widgetSelected( SelectionEvent e )
				{
				}

				public void widgetDefaultSelected( SelectionEvent e )
				{
				}

			} );

			btnData = new GridData( GridData.CENTER );
			btnData.widthHint = 20;
			btnData.heightHint = 20;
			btnDown = new Button( btnComposite, SWT.ARROW | SWT.DOWN );
			btnDown.setLayoutData( btnData );
			btnDown.addSelectionListener( new SelectionListener( ) {

				public void widgetSelected( SelectionEvent e )
				{

				}

				public void widgetDefaultSelected( SelectionEvent e )
				{
				}

			} );
		}

		if ( showMenus )
		{
			Menu menu = new Menu( viewer.getTable( ) );
			menu.addMenuListener( new MenuAdapter( ) {

				public void menuShown( MenuEvent e )
				{
					viewer.cancelEditing( );
				}
			} );
			itmRemove = new MenuItem( menu, SWT.NONE );
			itmRemove.setText( Messages.getString( "menu.menuItem.remove" ) ); //$NON-NLS-1$

			itmRemoveAll = new MenuItem( menu, SWT.NONE );
			itmRemoveAll.setText( Messages.getString( "menu.menuItem.removeAll" ) ); //$NON-NLS-1$

			viewer.getTable( ).setMenu( menu );
		}

		if ( enableKeyStrokes )
		{
			viewer.getTable( ).addKeyListener( new KeyListener( ) {

				public void keyPressed( KeyEvent e )
				{
				}

				public void keyReleased( KeyEvent e )
				{
					if ( e.keyCode == SWT.DEL )
					{
					}
				}

			} );
		}
	}

	/**
	 * get the table viewer
	 * 
	 * @return
	 */
	public TableViewer getViewer( )
	{
		return viewer;
	}

	/**
	 * get the main control
	 * 
	 * @return
	 */
	public Composite getControl( )
	{
		return mainControl;
	}

	/**
	 * get the up button
	 * 
	 * @return
	 */
	public Button getUpButton( )
	{
		return btnUp;
	}

	/**
	 * get the down button
	 * 
	 * @return
	 */
	public Button getDownButton( )
	{
		return btnDown;
	}

	/**
	 * get the remove button
	 * 
	 * @return
	 */
	public Button getRemoveButton( )
	{
		return btnRemove;
	}

	/**
	 * get the remove menu item
	 * 
	 * @return
	 */
	public MenuItem getRemoveMenuItem( )
	{
		return itmRemove;
	}

	/**
	 * get the remove all menu item
	 * 
	 * @return
	 */
	public MenuItem getRemoveAllMenuItem( )
	{
		return itmRemoveAll;
	}

	/**
	 * Accoring to the relation infomation of the query, refresh the table items
	 * 
	 * @param info
	 * @param tableName
	 * @return
	 */
	public List refresh( RelationInformation info, String tableName,
			Map columnMapping )
	{
		ArrayList columnsList = new ArrayList();
		if ( info == null )
			return columnsList;
		String[] columnName = info.getTableColumnNames( tableName );
		String[] columnType = new String[columnName.length];
		String[] columnPath = new String[columnName.length];
		ColumnMappingElement element = null;
		for ( int i = 0; i < columnName.length; i++ )
		{
			columnType[i] = info.getTableColumnType( tableName, columnName[i] );
			columnPath[i] = info.getTableOriginalColumnPath( tableName,
					columnName[i] );
			element = new ColumnMappingElement( );
			element.setColumnName( columnName[i] );
			element.setXPath( columnPath[i] );
			try
			{
				//Set type to its display name.
				element.setType( typeIdDisplayNameMapping.get(new Integer(DataTypes.getType(columnType[i]))).toString() );
			}
			catch ( OdaException e )
			{
				//Should not arrive here.
			}
			columnMapping.put( columnName[i], element );
			columnsList.add( element );
		}
		return columnsList;
	}
}