package lk.ijse.dep.web.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import lk.ijse.dep.web.model.Item;
import lk.ijse.dep.web.model.PlaceOrder;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Damika Anupama Nanayakkara <damikaanupama@gmail.com>
 * @since : 09/12/2020
 **/

@WebServlet(name = "PlaceOrderServlet", urlPatterns = "/pOrders")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicDataSource cp = (BasicDataSource) getServletContext().getAttribute("cp");
        try (Connection connection = cp.getConnection()) {
            Jsonb jsonb = JsonbBuilder.create();
            PlaceOrder placeOrder = jsonb.fromJson(req.getReader(), PlaceOrder.class);

            /* Validation Logic */
            if (placeOrder.getOrderId() == null || placeOrder.getItemCode() == null || placeOrder.getQty() == 0||placeOrder.getUnitPrice()==null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            if (!placeOrder.getOrderId().matches("D\\d{3}") || !placeOrder.getItemCode().matches("P\\d{3}") || Integer.toString(placeOrder.getQty()).trim().isEmpty()||placeOrder.getUnitPrice().toString().trim().isEmpty()) {

                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
            pstm.setString(1, placeOrder.getOrderId());
            pstm.setString(2, placeOrder.getItemCode());
            pstm.setString(3, Integer.toString(placeOrder.getQty()));
            pstm.setString(4, placeOrder.getUnitPrice().toString());
            if (pstm.executeUpdate() > 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLIntegrityConstraintViolationException  ex) {
            System.out.println("------------------------");

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }catch ( JsonbException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException throwables) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

}
