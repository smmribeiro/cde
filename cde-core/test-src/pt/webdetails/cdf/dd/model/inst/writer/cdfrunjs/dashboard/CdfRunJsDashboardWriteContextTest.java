/*!
 * Copyright 2002 - 2017 Webdetails, a Pentaho company. All rights reserved.
 *
 * This software was developed by Webdetails and is provided under the terms
 * of the Mozilla Public License, Version 2.0, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to http://mozilla.org/MPL/2.0/. The Initial Developer is Webdetails.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. Please refer to
 * the license for the specific language governing your rights and limitations.
 */

package pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import pt.webdetails.cdf.dd.CdeEngineForTests;
import pt.webdetails.cdf.dd.ICdeEnvironment;
import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.amd.CdfRunJsThingWriterFactory;

public class CdfRunJsDashboardWriteContextTest {
  private static CdfRunJsThingWriterFactory factory;

  private static final String indent = "";
  private static final boolean bypassCacheRead = true;

  @BeforeClass
  public static void setUp() throws Exception {
    ICdeEnvironment mockedEnvironment = Mockito.mock( ICdeEnvironment.class );
    Mockito.when( mockedEnvironment.getApplicationBaseContentUrl() ).thenReturn( "/pentaho/plugin/pentaho-cdf-dd" );
    new CdeEngineForTests( mockedEnvironment );

    factory = new CdfRunJsThingWriterFactory();
  }

  @Test
  public void testGetRoot() {
    // test an absolute path
    CdfRunJsDashboardWriteOptions options = new CdfRunJsDashboardWriteOptions(
        true,
        false,
        "localhost:8080",
        "http" );
    CdfRunJsDashboardWriteContext context = new CdfRunJsDashboardWriteContextForTesting(
        factory,
        indent,
        bypassCacheRead,
        options );

    Assert.assertEquals(
        "http://localhost:8080/pentaho/plugin/pentaho-cdf-dd",
        context.getRoot() );

    // test fallback to relative path if options.absRoot is invalid
    options = new CdfRunJsDashboardWriteOptions(
      true,
      false,
      "",
      "http" );
    context = new CdfRunJsDashboardWriteContextForTesting(
      factory,
      indent,
      bypassCacheRead,
      options );

    Assert.assertEquals(
      "/pentaho/plugin/pentaho-cdf-dd",
      context.getRoot() );

    // setup context for testing a relative path
    options = new CdfRunJsDashboardWriteOptions(
      false,
      false,
      null,
      "http" );
    context = new CdfRunJsDashboardWriteContextForTesting(
      factory,
      indent,
      bypassCacheRead,
      options );

    Assert.assertEquals(
      "/pentaho/plugin/pentaho-cdf-dd",
      context.getRoot() );
  }
}
