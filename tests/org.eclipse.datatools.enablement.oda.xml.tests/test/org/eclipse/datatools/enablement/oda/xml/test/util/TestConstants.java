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
package org.eclipse.datatools.enablement.oda.xml.test.util;

/**
 * This class host all the constants that are used in test.
 * 
 */
public class TestConstants
{
	private static String home = "";
	static{
		home = System.getProperty("xml.home");
		if( home == null )
			home = "";
		home = home==""?home:home+"/";
	}
	public static final String SMALL_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/small.xml";
	public static final String RECURSIVE_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/recursive.xml";
	public static final String CRITICAL_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/critical.xml";
	public static final String UTF8BOM = home+"test/org/eclipse/datatools/enablement/oda/xml/input/utf8bom.xml";
	public static final String RECURSIVE_DUPLICATENAME = home+"test/org/eclipse/datatools/enablement/oda/xml/input/recursiveDuplicateName.xml";
	
	public static final String BOOKSTORE_XML_FILE = home + "test/org/eclipse/datatools/enablement/oda/xml/input/BookStore.xml";
	public static final String BOOKSTORE_XSD_FILE = home + "test/org/eclipse/datatools/enablement/oda/xml/input/BookStore.xsd";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_INPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/SchemaPopulationUtilTest_getSchemaTree_XSD.xsd";
	public static final String TEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/test.xsd";
	public static final String TEST_XSD_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/input/nestedXSD.xsd";
	public static final String TEST_XSD_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/input/complex.xsd";
	public static final String TEST_XSD_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/selfrecursive.xsd";
	public static final String NESTED_COMPLEXTYPE_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/nestedcomplextype.xsd";
	public static final String DATATYPE_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/datatype.xsd";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_NEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_NEST_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_NEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_NEST_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_ATTR = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XSD_WITHOUT_ATTR.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD_WITHOUT_ATTR = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XSD_WITHOUT_ATTR.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_DATATYPE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_DATATYPE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_DATATYPE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_DATATYPE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_GROUP.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_GROUP.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_COMPLEX.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_COMPLEX.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_SELFRECURSIVE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_SELFRECURSIVE.txt";

	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITHOUT_ATTR = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML_WITHOUT_ATTR.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_WITHOUT_ATTR = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML_WITHOUT_ATTR.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_BOOKSTORE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML_BOOKSTORE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_BOOKSTORE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML_BOOKSTORE.txt";
	
	
	public static final String SAX_PARSER_TEST1_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test1.txt";
	public static final String SAX_PARSER_TEST1_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test1.txt";
	public static final String SAX_PARSER_TEST2_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test2.txt";
	public static final String SAX_PARSER_TEST2_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test2.txt";
	public static final String SAX_PARSER_TEST3_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test3.txt";
	public static final String SAX_PARSER_TEST3_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test3.txt";
	public static final String SAX_PARSER_TEST4_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test4.txt";
	public static final String SAX_PARSER_TEST4_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test4.txt";
	public static final String SAX_PARSER_TEST5_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test5.txt";
	public static final String SAX_PARSER_TEST5_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test5.txt";
	public static final String SAX_PARSER_TEST6_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test6.txt";
	public static final String SAX_PARSER_TEST6_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test6.txt";
	public static final String SAX_PARSER_TEST7_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test7.txt";
	public static final String SAX_PARSER_TEST7_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test7.txt";
	public static final String SAX_PARSER_TEST8_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test8.txt";
	public static final String SAX_PARSER_TEST8_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test8.txt";
	public static final String SAX_PARSER_TEST9_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test9.txt";
	public static final String SAX_PARSER_TEST9_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test9.txt";
	public static final String SAX_PARSER_TEST10_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test10.txt";
	public static final String SAX_PARSER_TEST10_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test10.txt";
	public static final String SAX_PARSER_TEST11_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test11.txt";
	public static final String SAX_PARSER_TEST11_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test11.txt";
	public static final String SAX_PARSER_TEST12_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test12.txt";
	public static final String SAX_PARSER_TEST12_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test12.txt";
	public static final String SAX_PARSER_TEST13_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test13.txt";
	public static final String SAX_PARSER_TEST13_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test13.txt";

	//public static final String SAX_PARSER_TESTLARGE_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.testLarge.txt";

	public static final String LARGE_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/large";

}