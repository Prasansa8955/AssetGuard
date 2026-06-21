package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineActivityLog;
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
public final class ActivityLogDao_Impl implements ActivityLogDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineActivityLog> __insertAdapterOfOfflineActivityLog;

  public ActivityLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineActivityLog = new EntityInsertAdapter<OfflineActivityLog>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_activity_logs` (`id`,`assetId`,`userId`,`userName`,`action`,`details`,`timestamp`,`ipAddress`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineActivityLog entity) {
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
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getUserId());
        }
        if (entity.getUserName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getUserName());
        }
        if (entity.getAction() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getAction());
        }
        if (entity.getDetails() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getDetails());
        }
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getIpAddress() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getIpAddress());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(9, _tmp);
      }
    };
  }

  @Override
  public Object insertActivityLog(final OfflineActivityLog log,
      final Continuation<? super Unit> $completion) {
    if (log == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineActivityLog.insert(_connection, log);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllActivityLogs(
      final Continuation<? super List<OfflineActivityLog>> $completion) {
    final String _sql = "SELECT * FROM offline_activity_logs ORDER BY timestamp DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _cursorIndexOfUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userName");
        final int _cursorIndexOfAction = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "action");
        final int _cursorIndexOfDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "details");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfIpAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ipAddress");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineActivityLog> _result = new ArrayList<OfflineActivityLog>();
        while (_stmt.step()) {
          final OfflineActivityLog _item;
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
          final String _tmpUserId;
          if (_stmt.isNull(_cursorIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_cursorIndexOfUserId);
          }
          final String _tmpUserName;
          if (_stmt.isNull(_cursorIndexOfUserName)) {
            _tmpUserName = null;
          } else {
            _tmpUserName = _stmt.getText(_cursorIndexOfUserName);
          }
          final String _tmpAction;
          if (_stmt.isNull(_cursorIndexOfAction)) {
            _tmpAction = null;
          } else {
            _tmpAction = _stmt.getText(_cursorIndexOfAction);
          }
          final String _tmpDetails;
          if (_stmt.isNull(_cursorIndexOfDetails)) {
            _tmpDetails = null;
          } else {
            _tmpDetails = _stmt.getText(_cursorIndexOfDetails);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final String _tmpIpAddress;
          if (_stmt.isNull(_cursorIndexOfIpAddress)) {
            _tmpIpAddress = null;
          } else {
            _tmpIpAddress = _stmt.getText(_cursorIndexOfIpAddress);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineActivityLog(_tmpId,_tmpAssetId,_tmpUserId,_tmpUserName,_tmpAction,_tmpDetails,_tmpTimestamp,_tmpIpAddress,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getPendingActivityLogs(
      final Continuation<? super List<OfflineActivityLog>> $completion) {
    final String _sql = "SELECT * FROM offline_activity_logs WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _cursorIndexOfUserName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userName");
        final int _cursorIndexOfAction = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "action");
        final int _cursorIndexOfDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "details");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfIpAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "ipAddress");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineActivityLog> _result = new ArrayList<OfflineActivityLog>();
        while (_stmt.step()) {
          final OfflineActivityLog _item;
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
          final String _tmpUserId;
          if (_stmt.isNull(_cursorIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_cursorIndexOfUserId);
          }
          final String _tmpUserName;
          if (_stmt.isNull(_cursorIndexOfUserName)) {
            _tmpUserName = null;
          } else {
            _tmpUserName = _stmt.getText(_cursorIndexOfUserName);
          }
          final String _tmpAction;
          if (_stmt.isNull(_cursorIndexOfAction)) {
            _tmpAction = null;
          } else {
            _tmpAction = _stmt.getText(_cursorIndexOfAction);
          }
          final String _tmpDetails;
          if (_stmt.isNull(_cursorIndexOfDetails)) {
            _tmpDetails = null;
          } else {
            _tmpDetails = _stmt.getText(_cursorIndexOfDetails);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final String _tmpIpAddress;
          if (_stmt.isNull(_cursorIndexOfIpAddress)) {
            _tmpIpAddress = null;
          } else {
            _tmpIpAddress = _stmt.getText(_cursorIndexOfIpAddress);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineActivityLog(_tmpId,_tmpAssetId,_tmpUserId,_tmpUserName,_tmpAction,_tmpDetails,_tmpTimestamp,_tmpIpAddress,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearActivityLogs(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_activity_logs";
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
