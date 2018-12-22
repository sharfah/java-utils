package com.sharfah.util.sql;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * Streams a ResultSet as CSV.
 */
public class StreamingCsvResultSetExtractor implements ResultSetExtractor<Void> {

  private static char DELIMITER = ',';

  private final OutputStream os;

  /**
   * @param os the OutputStream to stream the CSV to
   */
  public StreamingCsvResultSetExtractor(final OutputStream os) {
    this.os = os;
  }

  @Override
  public Void extractData(final ResultSet rs) {
    try (var pw = new PrintWriter(os, true)) {
      final var rsmd = rs.getMetaData();
      final var columnCount = rsmd.getColumnCount();
      writeHeader(rsmd, columnCount, pw);
      while (rs.next()) {
        for (var i = 1; i <= columnCount; i++) {
          final var value = rs.getObject(i);
          pw.write(value == null ? "" : value.toString());
          if (i != columnCount) {
            pw.append(DELIMITER);
          }
        }
        pw.println();
      }
      pw.flush();
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  private static void writeHeader(final ResultSetMetaData rsmd,
      final int columnCount, final PrintWriter pw) throws SQLException {
    for (var i = 1; i <= columnCount; i++) {
      pw.write(rsmd.getColumnName(i));
      if (i != columnCount) {
        pw.append(DELIMITER);
      }
    }
    pw.println();
  }
}
