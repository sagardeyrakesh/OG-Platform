/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.server.push.web;

import com.opengamma.OpenGammaRuntimeException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * <p>Sets up a connection for a client.  A connection corresponds to one view, e.g. a single browser tab or
 * window.  A user can have multiple simultaneous client connections.  The client ID that identifies the
 * connection is sent in the response as JSON:</p>
 * {@code {"clientId": <clientId>}}.
 * <p>This ID must be passed to all other operations for this client.</p>

 */
public class HandshakeServlet extends SpringConfiguredServlet {

  @Autowired
  private LongPollingConnectionManager _connectionManager;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userId = req.getRemoteUser(); // TODO is this right?
    String clientId = _connectionManager.handshake(userId);
    resp.setContentType(MediaType.APPLICATION_JSON);
    JSONObject json = new JSONObject();
    try {
      json.put("clientId", clientId);
    } catch (JSONException e) {
      throw new OpenGammaRuntimeException("Unexpected problem creating JSON", e);
    }
    resp.getWriter().write(json.toString());
  }
}
