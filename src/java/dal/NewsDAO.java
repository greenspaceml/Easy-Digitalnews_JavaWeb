package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.News;

public class NewsDAO {

    //Find the Newss in database by its ID
    public News getNewByID(int id) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext dBContext = new DBContext();
        News n = null;
        //sql statament
        String sql = "Select * from DigitalNews \n"
                + "where ID = ?";
        try {
            //trying connect to database
            connection = dBContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            //get the new out from databse
            while (rs.next()) {
                n = new News();
                n.setId(rs.getInt("ID"));
                n.setTitle(rs.getString("title"));
                n.setDescription(rs.getString("description"));
                n.setImage(rs.getString("image"));
                n.setAuthor(rs.getString("author"));
                n.setTimePost(rs.getDate("timePost"));
                n.setShortDes(rs.getString("shortDes"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close all connection
            dBContext.closeConnection(connection, ps, rs);
        }
        return n;
    }

    //get the top 1 new by the time posted and return to the called function
    public News getNewestNew() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        News n = new News();
        //sql statement
        String sql = "Select top 1 * from DigitalNews order by timePost desc";
        DBContext dBContext = new DBContext();
        try {
            //trying connect to database
            connection = dBContext.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            //get the news out from databse
            while (rs.next()) {
                n.setId(rs.getInt("ID"));
                n.setTitle(rs.getString("title"));
                n.setDescription(rs.getString("description"));
                n.setImage(rs.getString("image"));
                n.setAuthor(rs.getString("author"));
                n.setTimePost(rs.getDate("timePost"));
                n.setShortDes(rs.getString("shortDes"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close all connection
            dBContext.closeConnection(connection, ps, rs);
        }
        return n;
    }

    //get the top 5 news by the time posted and return to the called function
    public ArrayList<News> get5NewsByMostRecents() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<News> listNews = new ArrayList<>();
        DBContext dBContext = new DBContext();
        //sql statement
        String sql = "Select top 5 * from DigitalNews order by timePost desc";
        try {
            //trying connect to database
            connection = dBContext.getConnection();
            ps = connection.prepareCall(sql);
            rs = ps.executeQuery();
            //get all news out from databse
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("ID"));
                n.setTitle(rs.getString("title"));
                n.setDescription(rs.getString("description"));
                n.setImage(rs.getString("image"));
                n.setAuthor(rs.getString("author"));
                n.setTimePost(rs.getDate("timePost"));
                n.setShortDes(rs.getString("shortDes"));
                listNews.add(n);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close all connection
            dBContext.closeConnection(connection, ps, rs);
        }
        return listNews;
    }

    //count the number of record searched and return counted number
    public int RecordCounter(String textSearch) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //sql statement
        String sql = "SELECT COUNT(*) FROM DigitalNews\n"
                + "where title like ?";
        DBContext dBContext = new DBContext();
        try {
            //trying connect to database
            connection = dBContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + textSearch + "%");
            rs = ps.executeQuery();
            //get the number of record from database
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close all connection
            dBContext.closeConnection(connection, ps, rs);
        }
        return -1;
    }

    //Search and page the searched records
    public ArrayList<News> Searching(String textSearch, int pageIndex, int pageSize) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<News> listNews = new ArrayList<>();
        DBContext dBContext = new DBContext();
        //sql statement
        String sql = "SELECT *\n"
                + "  FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY timePost DESC) as row_num \n"
                + "		FROM  DigitalNews\n"
                + "		where title like ?) news\n"
                + "  where row_num >= ? and row_num <= ?";
        try {
            //trying connect to database
            connection = dBContext.getConnection();
            ps = connection.prepareStatement(sql);
            //adding search materials to sql statement
            ps.setString(1, "%" + textSearch + "%");
            ps.setInt(2, ((pageIndex - 1) * pageSize + 1));
            ps.setInt(3, (pageIndex * pageSize));
            rs = ps.executeQuery();
            //get the searched news out from databse
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getInt("ID"));
                n.setTitle(rs.getString("title"));
                n.setDescription(rs.getString("description"));
                n.setImage(rs.getString("image"));
                n.setAuthor(rs.getString("author"));
                n.setTimePost(rs.getDate("timePost"));
                n.setShortDes(rs.getString("shortDes"));
                listNews.add(n);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //close all connection
            dBContext.closeConnection(connection, ps, rs);
        }
        return listNews;
    }

}
