package com.sap.cloud.s4hana.examples.addressmgr;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.sap.cloud.s4hana.examples.addressmgr.commands.GetAllBusinessPartnersCommand;
import com.sap.cloud.s4hana.examples.addressmgr.commands.GetSingleBusinessPartnerByIdCommand;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/api/k8s-example")
public class K8sExampleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(K8sExampleServlet.class);



    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {


        final String jsonResult;
        try {
            logger.info("call target example app");

            JSONObject jsonObj = new JSONObject(System.getenv("VCAP_SERVICES"));
            JSONArray jsonArr = jsonObj.getJSONArray("connectivity");
            JSONObject credentials = jsonArr.getJSONObject(0).getJSONObject("credentials");
            jsonResult = new Gson().toJson(credentials);
            String clientid = credentials.getString("clientid");
//            response.setContentType("application/json");
            response.setContentType("text/plain");
            response.getWriter().write(clientid);
        } catch (Exception e) {
            logger.error("Error occured while handling request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occured while handling request: " + e.toString());
        }
    }

}
