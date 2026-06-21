package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineReport;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class ReportDao_Impl implements ReportDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineReport> __insertAdapterOfOfflineReport;

  public ReportDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineReport = new EntityInsertAdapter<OfflineReport>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_reports` (`id`,`reportType`,`title`,`timestamp`,`dataJson`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineReport entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getReportType() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getReportType());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getTitle());
        }
        statement.bindLong(4, entity.getTimestamp());
        if (entity.getDataJson() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDataJson());
        }
      }
    };
  }

  @Override
  public Object insertReport(final OfflineReport report,
      final Continuation<? super Unit> $completion) {
    if (report == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineReport.insert(_connection, report);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllReports(final Continuation<? super List<OfflineReport>> $completion) {
    final String _sql = "SELECT * FROM offline_reports ORDER BY timestamp DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfReportType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reportType");
        final int _cursorIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfDataJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "dataJson");
        final List<OfflineReport> _result = new ArrayList<OfflineReport>();
        while (_stmt.step()) {
          final OfflineReport _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpReportType;
          if (_stmt.isNull(_cursorIndexOfReportType)) {
            _tmpReportType = null;
          } else {
            _tmpReportType = _stmt.getText(_cursorIndexOfReportType);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_cursorIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_cursorIndexOfTitle);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final String _tmpDataJson;
          if (_stmt.isNull(_cursorIndexOfDataJson)) {
            _tmpDataJson = null;
          } else {
            _tmpDataJson = _stmt.getText(_cursorIndexOfDataJson);
          }
          _item = new OfflineReport(_tmpId,_tmpReportType,_tmpTitle,_tmpTimestamp,_tmpDataJson);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearReports(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_reports";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
