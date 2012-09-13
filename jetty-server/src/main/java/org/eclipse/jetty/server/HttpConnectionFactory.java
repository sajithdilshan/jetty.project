//
//  ========================================================================
//  Copyright (c) 1995-2012 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//


package org.eclipse.jetty.server;


import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.io.EndPoint;

public class HttpConnectionFactory extends AbstractConnectionFactory implements HttpChannelConfig.ConnectionFactory
{
    private final HttpChannelConfig _config;

    public HttpConnectionFactory()
    {
        this(new HttpChannelConfig());
        setInputBufferSize(16384);
    }
    
    public HttpConnectionFactory(HttpChannelConfig config)
    {
        super(HttpVersion.HTTP_1_1.toString());
        _config=config;
        addBean(_config);
    }
    
    @Override
    public HttpChannelConfig getHttpChannelConfig()
    {
        return _config;
    }

    @Override
    public Connection newConnection(Connector connector, EndPoint endPoint)
    {
        HttpConnection connection = new HttpConnection(_config, connector, endPoint);
        connection.setInputBufferSize(getInputBufferSize()); // TODO constructor injection
        return connection;
    }
    
}