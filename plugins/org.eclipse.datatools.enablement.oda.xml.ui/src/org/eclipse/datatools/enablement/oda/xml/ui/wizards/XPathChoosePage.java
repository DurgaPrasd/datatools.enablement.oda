/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.List;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.preference.DataSetPreferencePage;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Root xpath choose page. It expands the xml tree list to choose the prefered
 * root path
 */

public class XPathChoosePage extends DataSetWizardPage
{
	private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );
	
	private transient Tree availableXmlTree;
	private transient Button btnAdd;
	private transient Composite btnComposite;
	private transient Text xmlPathText;
	private transient Group treeGroup;
	private transient Group rightGroup;
	
	private ATreeNode treeNode;
	private TreeItem selectedItem;
	private String xsdFileName;
	private String xmlFileName;
	private String xmlEncoding;

	private String rootPath;
	private String initRootPath;

	/**
	 * @param string
	 */
	public XPathChoosePage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) );
	}

	/**
	 * @param pageName
	 */
	public XPathChoosePage( String pageName )
	{
		super( pageName );
		this.setTitle( pageName );
		this.setMessage( DEFAULT_MESSAGE );
		this.setPageComplete( false );
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		
		if( XMLInformationHolder.hasDestroyed() )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		populateXMLTree( );
		
		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_XML_XPATH );
	}
	
	/**
	 * initial the info after create the control
	 * 
	 */
	private void initializeControl( )
	{
		xsdFileName = getXSDFileURI( );
		xmlFileName = getInitXMLFileURI( );
		xmlEncoding = getXMLEncoding( );
	
		String queryText = getInitQueryText( );

		String tableName = XMLRelationInfoUtil.getTableName( queryText );
		if ( tableName != null )
			rootPath = XMLRelationInfoUtil.getXPathExpression( queryText,
					tableName );
		else
			rootPath = "";

		backupRootPath( );
		if ( rootPath != null && rootPath.length( ) > 0 )
			xmlPathText.setText( rootPath );
	}
	
	private void backupRootPath( )
	{
		initRootPath = rootPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		DEFAULT_MESSAGE = Messages.getString( "xPathChoosePage.messages.rowMapping" );
		XMLInformationHolder.start( dataSetDesign );
		
		refresh( );
	}
	
	protected void refresh( )
	{
		xmlFileName = getXMLFileURI( );
		xsdFileName = getXSDFileURI( );
		xmlEncoding = getXMLEncoding( );
		populateXMLTree( );
		backupRootPath( );
		setMessage( DEFAULT_MESSAGE );
	}
	
	protected String getXSDFileURI( )
	{
		return XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
	}

	protected String getInitXMLFileURI( )
	{
		return getXMLFileURI( );
	}

	protected String getXMLEncoding( )
	{
		return XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST);
	}
	
	protected String getXMLFileURI( )
	{
		return XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
	}
	
	protected String getInitQueryText( )
	{
		return XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );
	}

    /**
    * 
    * @param parent
    * @return
    */
	public Control createPageControl( Composite parent )
	{
		DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );
		this.setMessage( DEFAULT_MESSAGE );
		Composite composite = new Composite( parent, SWT.NONE );

		FormLayout layout = new FormLayout( );
		composite.setLayout( layout );

		createLeftGroup( composite );

		FormData data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );

		btnComposite = new Composite( composite, SWT.NONE );
		btnComposite.setLayoutData( data );
		FillLayout btnLayout = new FillLayout( SWT.VERTICAL );
		btnLayout.spacing = 3;
		btnComposite.setLayout( btnLayout );

		btnAdd = new Button( btnComposite, SWT.NONE );
		btnAdd.setText( ">" ); //$NON-NLS-1$
		btnAdd.setEnabled( false );

		//TODO To externalize into messages
		btnAdd.setToolTipText( "Use the selected node as XPath expression" );
		btnAdd.addSelectionListener( new SelectionAdapter( ) {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{

				List xpathList = getSelectedXPath( );
				if ( xpathList != null )
				{
					RowMappingDialog dialog = new RowMappingDialog( PlatformUI.getWorkbench( )
							.getDisplay( )
							.getActiveShell( ),
							Messages.getString( "RowMappingDialog.title" ),
							xpathList ); //$NON-NLS-1$
					if ( dialog.open( ) == Window.OK )
					{
						rootPath = dialog.getSelectedPath( );
						xmlPathText.setText( rootPath );
					}
				}
			}
		} );

		createRightGroup( composite );
		return composite;
	}
	
	/**
	 * create left group composite
	 * @param composite2
	 */
	private void createLeftGroup( Composite composite2 )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 47, -5 );
		data.bottom = new FormAttachment( 100, -5 );

		treeGroup = new Group( composite2, SWT.NONE );
		treeGroup.setLayout( new FillLayout( ) );
		treeGroup.setLayoutData( data );
		availableXmlTree = new Tree( treeGroup, SWT.MULTI
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		availableXmlTree.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				TreeItem items[] = availableXmlTree.getSelection( );
				for ( int i = 0; i < items.length; i++ )
				{
					selectedItem = items[0];
					if ( items[i].getGrayed( ) )
					{
						availableXmlTree.setRedraw( false );
						availableXmlTree.deselectAll( );
						availableXmlTree.setRedraw( true );
						availableXmlTree.redraw( );
					}
				}
				if ( selectedItem != null )
					btnAdd.setEnabled( true );
				else
					btnAdd.setEnabled( false );

			}

		} );

		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) );
	}
	
	/**
	 * create right group composite
	 * @param composite2
	 */
	private void createRightGroup( Composite composite2 )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( btnComposite, 5 );
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		rightGroup = new Group( composite2, SWT.NONE );

		rightGroup.setLayout( new FormLayout( ) );
		rightGroup.setText( Messages.getString( "xPathChoosePage.messages.rowMapping" ) );
		rightGroup.setLayoutData( data );

		data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 100, -5 );

		final Label label = new Label( rightGroup, SWT.NONE );
		label.setText( Messages.getString( "xPathChoosePage.messages.xPathExpression" ) );
		label.setLayoutData( data );

		data = new FormData( );
		data.top = new FormAttachment( 0, 25 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 100, -5 );
		xmlPathText = new Text( rightGroup, SWT.BORDER );
		xmlPathText.setLayoutData( data );

		xmlPathText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				rootPath = xmlPathText.getText( );
				setPageStatus( );
			}
		} );
	}

	/**
	 * get the standby XPath expression
	 * 
	 * @return
	 */
	protected List getSelectedXPath( )
	{
		TreeItem selected = this.selectedItem;
		
		if ( selected.getData( ) instanceof TreeNodeData )
		{
			ATreeNode node = ((TreeNodeData) selected.getData( )).getTreeNode();
			if ( node.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
			{
				return null;
			}
			else
			{
				rootPath = "/" + selected.getText( );
			}
		}

		while ( selected.getParentItem( ) != null )
		{
			selected = selected.getParentItem( );
			if ( selected.getData( ) instanceof TreeNodeData )
			{
				ATreeNode node = ((TreeNodeData) selected.getData( )).getTreeNode();
				if ( node.getType( ) == ATreeNode.ELEMENT_TYPE )
				{
					rootPath = "/" + selected.getText( ) + rootPath;
				}
			}
		}
		return XPathPopulationUtil.populateRootPath( rootPath );
	}

	/**
	 * populate xml tree
	 * 
	 */
	private void populateXMLTree( )
	{
		try
		{
			availableXmlTree.removeAll( );
			if ( ( xsdFileName != null && xsdFileName.trim( ).length( ) > 0 )
					|| ( xmlFileName != null && xmlFileName.trim( ).length( ) > 0 ) )
			{
				int numberOfElement = 0;
				Preferences preferences = UiPlugin.getDefault( )
						.getPluginPreferences( );
				if ( preferences.contains( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED ) )
				{
					numberOfElement = preferences.getInt( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED );
				}
				else
				{
					numberOfElement = DataSetPreferencePage.DEFAULT_MAX_NUM_OF_ELEMENT_PARSED;
					preferences.setValue( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED,
							numberOfElement );
				}
				// TODO for migrate into ODA3.0, the relative path is not
				// supported
				// Object url = this.dataSetHandle.getModuleHandle(
				// ).findResource( fileName,IResourceLocator.LIBRARY );
				//				
				// if( url != null )
				treeNode = SchemaPopulationUtil.getSchemaTree( xsdFileName, xmlFileName,xmlEncoding, numberOfElement );
				if ( treeNode == null || treeNode.getChildren( ).length == 0 )
				{
					OdaException ex = new OdaException( Messages.getString( "dataset.error.populateXMLTree" ) );
					ExceptionHandler.showException( getShell( ),
							Messages.getString( "error.label" ),
							ex.getMessage( ),
							ex );
				}
				else
				{
					Object[] childs = treeNode.getChildren( );
					availableXmlTree.addListener(SWT.Expand, new Listener(){

						public void handleEvent(Event event) {
							TreeItem currentItem = (TreeItem)event.item;
							
							if ( ((TreeNodeData)currentItem.getData()).hasBeenExpandedOnce())
								return;
							
							((TreeNodeData)currentItem.getData()).setHasBeenExpandedOnce();
							currentItem.removeAll();
							try
							{
								if ( (((TreeNodeData)currentItem.getData()).getTreeNode()).getChildren( ) != null
										&& ((TreeNodeData)currentItem.getData()).getTreeNode().getChildren( ).length > 0 )
									TreePopulationUtil.populateTreeItems( currentItem, ((TreeNodeData)currentItem.getData()).getTreeNode().getChildren( ), false );
							}
							catch ( OdaException e )
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}});
					
					TreePopulationUtil.populateTreeItems( availableXmlTree, childs, false );
				}
			}
		}
		catch ( Exception e )
		{
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),
					e.getMessage( ),
					e );
		}
	}
	
	/**
	 * set page status based on row number
	 *
	 */
	private void setPageStatus( )
	{
		if ( !isRootPathValid( ) )
		{
			setPageComplete( false );
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",
					new Object[]{rootPath == null ? "" : rootPath} ), IMessageProvider.ERROR );
		}
		else
		{
			if ( initRootPath != null
					&& !initRootPath.equals( "" )
					&& !initRootPath.equals( rootPath ) )
			{
				setMessage( Messages.getString( "xPathChoosePage.messages.xpathChange" ),
						INFORMATION );
			}
			else
			{
				setMessage( DEFAULT_MESSAGE );
			}
			setPageComplete( true );
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isRootPathValid( )
	{
		return !(rootPath == null
				|| rootPath.trim( ).length( ) == 0);
	}
	
	/**
	 * when XPath text has changed, reset the dataSetHandle.CONST_PROP_XPATH
	 * 
	 */
	private void resetXPathText( String pathStr )
	{
		if ( pathStr != null && pathStr.trim( ).length( ) > 0 )
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_XPATH,
					pathStr );
			// The relation information should be changed
			if ( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME ) != null )
			{
				String relationInfo = (String) XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );

				if ( relationInfo == null
						|| relationInfo.trim( ).length( ) == 0 )
					return;
				String tableName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME );
				String infoStr = XMLRelationInfoUtil.replaceXpathExpression( tableName,
						relationInfo,
						pathStr );

				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						infoStr );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		if ( isValid( ) )
		{
			IWizardPage page = super.getNextPage( );
			if ( page instanceof ColumnMappingPage )
			{
				( (ColumnMappingPage) page ).refresh( );
			}
			return page;
		}
		else
			return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		if ( rootPath == null || rootPath.trim( ).length( ) == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    
	/**
	 * the xpath expression is valid or not
	 * @return
	 */
	private boolean isValid( )
	{
		rootPath = xmlPathText.getText( );
		if ( !isRootPathValid( ) )
		{
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",
					new Object[]{rootPath == null ? "" : rootPath} ), IMessageProvider.ERROR );
			return false;
		}
		else
		{
			resetXPathText( rootPath );
			return true;
		}
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		try
		{
			resetXPathText( rootPath );
			savePage( design );
		}
		catch ( OdaException e )
		{
		}
		return design;
	}
    
	/**
     * Updates the given dataSetDesign with the query and its metadata defined
     * in this page.
     * 
     * @param dataSetDesign
     * @throws OdaException 
     */
    private void savePage( DataSetDesign dataSetDesign ) throws OdaException
	{
    	if( XMLInformationHolder.hasDestroyed( ) )
    		return;
		if ( dataSetDesign != null
				&& getQueryText( dataSetDesign ) != null
				&& !getQueryText( dataSetDesign )
						.equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
		{
			setQueryText( dataSetDesign,
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			updateDesign( dataSetDesign );
		}
	}
    
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		DataSetDesignPopulator.populateResultSet( dataSetDesign );
	}

	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		return dataSetDesign.getQueryText( );
	}

	protected void setQueryText( DataSetDesign dataSetDesign, String queryText )
	{
		dataSetDesign.setQueryText( queryText );
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
    protected boolean canLeave( )
	{
		return isValid( );
	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
    protected void cleanup()
    {
    	XMLInformationHolder.destory( );
    }	

}