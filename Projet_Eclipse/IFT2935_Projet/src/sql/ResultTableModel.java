package sql;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by lacombam on 05/05/17.
 */
public class ResultTableModel extends AbstractTableModel {

    private ResultSet results;
    private ResultSetMetaData meta;

    public ResultTableModel(ResultSet results) {

        this.results = results;

        try {
            this.meta = results.getMetaData();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        try
        {
            return meta.getColumnCount();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getColumnName( int column )
    {
        try
        {
            return meta.getColumnName( column + 1 );
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public int getRowCount() {
        try
        {
            results.last();
            return results.getRow();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        try
        {
            results.absolute( row + 1 );
            return results.getObject(column + 1 );
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
