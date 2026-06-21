package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineAuditLog;
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
public final class AuditDao_Impl implements AuditDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineAuditLog> __insertAdapterOfOfflineAuditLog;

  public AuditDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineAuditLog = new EntityInsertAdapter<OfflineAuditLog>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_audit_logs` (`id`,`assetId`,`assetName`,`auditorName`,`timestamp`,`latitude`,`longitude`,`siteId`,`siteName`,`isNearSite`,`condition`,`statusDescription`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAuditLog entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getAssetId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getAssetId());
        }
        if (entity.getAssetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAssetName());
        }
        if (entity.getAuditorName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getAuditorName());
        }
        statement.bindLong(5, entity.getTimestamp());
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
        if (entity.getSiteId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSiteId());
        }
        if (entity.getSiteName() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getSiteName());
        }
        final int _tmp = entity.isNearSite() ? 1 : 0;
        statement.bindLong(10, _tmp);
        if (entity.getCondition() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getCondition());
        }
        if (entity.getStatusDescription() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getStatusDescription());
        }
        final int _tmp_1 = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(13, _tmp_1);
      }
    };
  }

  @Override
  public Object insertAudit(final OfflineAuditLog audit,
      final Continuation<? super Unit> $completion) {
    if (audit == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineAuditLog.insert(_connection, audit);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllAudits(final Continuation<? super List<OfflineAuditLog>> $completion) {
    final String _sql = "SELECT * FROM offline_audit_logs ORDER BY timestamp DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfAuditorName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "auditorName");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
        final int _cursorIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
        final int _cursorIndexOfSiteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "siteId");
        final int _cursorIndexOfSiteName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "siteName");
        final int _cursorIndexOfIsNearSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isNearSite");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfStatusDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "statusDescription");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAuditLog> _result = new ArrayList<OfflineAuditLog>();
        while (_stmt.step()) {
          final OfflineAuditLog _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpAssetId;
          if (_stmt.isNull(_cursorIndexOfAssetId)) {
            _tmpAssetId = null;
          } else {
            _tmpAssetId = _stmt.getText(_cursorIndexOfAssetId);
          }
          final String _tmpAssetName;
          if (_stmt.isNull(_cursorIndexOfAssetName)) {
            _tmpAssetName = null;
          } else {
            _tmpAssetName = _stmt.getText(_cursorIndexOfAssetName);
          }
          final String _tmpAuditorName;
          if (_stmt.isNull(_cursorIndexOfAuditorName)) {
            _tmpAuditorName = null;
          } else {
            _tmpAuditorName = _stmt.getText(_cursorIndexOfAuditorName);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final double _tmpLatitude;
          _tmpLatitude = _stmt.getDouble(_cursorIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _stmt.getDouble(_cursorIndexOfLongitude);
          final String _tmpSiteId;
          if (_stmt.isNull(_cursorIndexOfSiteId)) {
            _tmpSiteId = null;
          } else {
            _tmpSiteId = _stmt.getText(_cursorIndexOfSiteId);
          }
          final String _tmpSiteName;
          if (_stmt.isNull(_cursorIndexOfSiteName)) {
            _tmpSiteName = null;
          } else {
            _tmpSiteName = _stmt.getText(_cursorIndexOfSiteName);
          }
          final boolean _tmpIsNearSite;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsNearSite));
          _tmpIsNearSite = _tmp != 0;
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpStatusDescription;
          if (_stmt.isNull(_cursorIndexOfStatusDescription)) {
            _tmpStatusDescription = null;
          } else {
            _tmpStatusDescription = _stmt.getText(_cursorIndexOfStatusDescription);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp_1 != 0;
          _item = new OfflineAuditLog(_tmpId,_tmpAssetId,_tmpAssetName,_tmpAuditorName,_tmpTimestamp,_tmpLatitude,_tmpLongitude,_tmpSiteId,_tmpSiteName,_tmpIsNearSite,_tmpCondition,_tmpStatusDescription,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getPendingAudits(final Continuation<? super List<OfflineAuditLog>> $completion) {
    final String _sql = "SELECT * FROM offline_audit_logs WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfAuditorName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "auditorName");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
        final int _cursorIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
        final int _cursorIndexOfSiteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "siteId");
        final int _cursorIndexOfSiteName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "siteName");
        final int _cursorIndexOfIsNearSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isNearSite");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfStatusDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "statusDescription");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAuditLog> _result = new ArrayList<OfflineAuditLog>();
        while (_stmt.step()) {
          final OfflineAuditLog _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpAssetId;
          if (_stmt.isNull(_cursorIndexOfAssetId)) {
            _tmpAssetId = null;
          } else {
            _tmpAssetId = _stmt.getText(_cursorIndexOfAssetId);
          }
          final String _tmpAssetName;
          if (_stmt.isNull(_cursorIndexOfAssetName)) {
            _tmpAssetName = null;
          } else {
            _tmpAssetName = _stmt.getText(_cursorIndexOfAssetName);
          }
          final String _tmpAuditorName;
          if (_stmt.isNull(_cursorIndexOfAuditorName)) {
            _tmpAuditorName = null;
          } else {
            _tmpAuditorName = _stmt.getText(_cursorIndexOfAuditorName);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final double _tmpLatitude;
          _tmpLatitude = _stmt.getDouble(_cursorIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _stmt.getDouble(_cursorIndexOfLongitude);
          final String _tmpSiteId;
          if (_stmt.isNull(_cursorIndexOfSiteId)) {
            _tmpSiteId = null;
          } else {
            _tmpSiteId = _stmt.getText(_cursorIndexOfSiteId);
          }
          final String _tmpSiteName;
          if (_stmt.isNull(_cursorIndexOfSiteName)) {
            _tmpSiteName = null;
          } else {
            _tmpSiteName = _stmt.getText(_cursorIndexOfSiteName);
          }
          final boolean _tmpIsNearSite;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsNearSite));
          _tmpIsNearSite = _tmp != 0;
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpStatusDescription;
          if (_stmt.isNull(_cursorIndexOfStatusDescription)) {
            _tmpStatusDescription = null;
          } else {
            _tmpStatusDescription = _stmt.getText(_cursorIndexOfStatusDescription);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp_1 != 0;
          _item = new OfflineAuditLog(_tmpId,_tmpAssetId,_tmpAssetName,_tmpAuditorName,_tmpTimestamp,_tmpLatitude,_tmpLongitude,_tmpSiteId,_tmpSiteName,_tmpIsNearSite,_tmpCondition,_tmpStatusDescription,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearAudits(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_audit_logs";
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
