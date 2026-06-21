package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineNotification;
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
public final class NotificationDao_Impl implements NotificationDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineNotification> __insertAdapterOfOfflineNotification;

  public NotificationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineNotification = new EntityInsertAdapter<OfflineNotification>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_notifications` (`id`,`title`,`message`,`timestamp`,`targetUserId`,`isRead`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineNotification entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getMessage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getMessage());
        }
        statement.bindLong(4, entity.getTimestamp());
        if (entity.getTargetUserId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getTargetUserId());
        }
        final int _tmp = entity.isRead() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
  }

  @Override
  public Object insertNotification(final OfflineNotification notification,
      final Continuation<? super Unit> $completion) {
    if (notification == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineNotification.insert(_connection, notification);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllNotifications(
      final Continuation<? super List<OfflineNotification>> $completion) {
    final String _sql = "SELECT * FROM offline_notifications ORDER BY timestamp DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _cursorIndexOfMessage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "message");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfTargetUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetUserId");
        final int _cursorIndexOfIsRead = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isRead");
        final List<OfflineNotification> _result = new ArrayList<OfflineNotification>();
        while (_stmt.step()) {
          final OfflineNotification _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_cursorIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_cursorIndexOfTitle);
          }
          final String _tmpMessage;
          if (_stmt.isNull(_cursorIndexOfMessage)) {
            _tmpMessage = null;
          } else {
            _tmpMessage = _stmt.getText(_cursorIndexOfMessage);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final String _tmpTargetUserId;
          if (_stmt.isNull(_cursorIndexOfTargetUserId)) {
            _tmpTargetUserId = null;
          } else {
            _tmpTargetUserId = _stmt.getText(_cursorIndexOfTargetUserId);
          }
          final boolean _tmpIsRead;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsRead));
          _tmpIsRead = _tmp != 0;
          _item = new OfflineNotification(_tmpId,_tmpTitle,_tmpMessage,_tmpTimestamp,_tmpTargetUserId,_tmpIsRead);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getNotificationsForUser(final String userId,
      final Continuation<? super List<OfflineNotification>> $completion) {
    final String _sql = "SELECT * FROM offline_notifications WHERE targetUserId = ? ORDER BY timestamp DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _cursorIndexOfMessage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "message");
        final int _cursorIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _cursorIndexOfTargetUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetUserId");
        final int _cursorIndexOfIsRead = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isRead");
        final List<OfflineNotification> _result = new ArrayList<OfflineNotification>();
        while (_stmt.step()) {
          final OfflineNotification _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpTitle;
          if (_stmt.isNull(_cursorIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_cursorIndexOfTitle);
          }
          final String _tmpMessage;
          if (_stmt.isNull(_cursorIndexOfMessage)) {
            _tmpMessage = null;
          } else {
            _tmpMessage = _stmt.getText(_cursorIndexOfMessage);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_cursorIndexOfTimestamp);
          final String _tmpTargetUserId;
          if (_stmt.isNull(_cursorIndexOfTargetUserId)) {
            _tmpTargetUserId = null;
          } else {
            _tmpTargetUserId = _stmt.getText(_cursorIndexOfTargetUserId);
          }
          final boolean _tmpIsRead;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsRead));
          _tmpIsRead = _tmp != 0;
          _item = new OfflineNotification(_tmpId,_tmpTitle,_tmpMessage,_tmpTimestamp,_tmpTargetUserId,_tmpIsRead);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearNotifications(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_notifications";
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
