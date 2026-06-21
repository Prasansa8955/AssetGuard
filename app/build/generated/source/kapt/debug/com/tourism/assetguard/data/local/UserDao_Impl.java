package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineUser;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineUser> __insertAdapterOfOfflineUser;

  private final EntityDeleteOrUpdateAdapter<OfflineUser> __updateAdapterOfOfflineUser;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineUser = new EntityInsertAdapter<OfflineUser>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_users` (`userId`,`name`,`email`,`phone`,`profileImage`,`role`,`isEnabled`,`permissionsJson`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineUser entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getEmail());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getPhone());
        }
        if (entity.getProfileImage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getProfileImage());
        }
        if (entity.getRole() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getRole());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getPermissionsJson() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getPermissionsJson());
        }
      }
    };
    this.__updateAdapterOfOfflineUser = new EntityDeleteOrUpdateAdapter<OfflineUser>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `offline_users` SET `userId` = ?,`name` = ?,`email` = ?,`phone` = ?,`profileImage` = ?,`role` = ?,`isEnabled` = ?,`permissionsJson` = ? WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineUser entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getEmail());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getPhone());
        }
        if (entity.getProfileImage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getProfileImage());
        }
        if (entity.getRole() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getRole());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getPermissionsJson() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getPermissionsJson());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getUserId());
        }
      }
    };
  }

  @Override
  public Object insertUser(final OfflineUser user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineUser.insert(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateUser(final OfflineUser user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOfflineUser.handle(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllUsers(final Continuation<? super List<OfflineUser>> $completion) {
    final String _sql = "SELECT * FROM offline_users";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _cursorIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _cursorIndexOfProfileImage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profileImage");
        final int _cursorIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _cursorIndexOfIsEnabled = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isEnabled");
        final int _cursorIndexOfPermissionsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "permissionsJson");
        final List<OfflineUser> _result = new ArrayList<OfflineUser>();
        while (_stmt.step()) {
          final OfflineUser _item;
          final String _tmpUserId;
          if (_stmt.isNull(_cursorIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_cursorIndexOfUserId);
          }
          final String _tmpName;
          if (_stmt.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_cursorIndexOfName);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_cursorIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_cursorIndexOfEmail);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_cursorIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_cursorIndexOfPhone);
          }
          final String _tmpProfileImage;
          if (_stmt.isNull(_cursorIndexOfProfileImage)) {
            _tmpProfileImage = null;
          } else {
            _tmpProfileImage = _stmt.getText(_cursorIndexOfProfileImage);
          }
          final String _tmpRole;
          if (_stmt.isNull(_cursorIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_cursorIndexOfRole);
          }
          final boolean _tmpIsEnabled;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsEnabled));
          _tmpIsEnabled = _tmp != 0;
          final String _tmpPermissionsJson;
          if (_stmt.isNull(_cursorIndexOfPermissionsJson)) {
            _tmpPermissionsJson = null;
          } else {
            _tmpPermissionsJson = _stmt.getText(_cursorIndexOfPermissionsJson);
          }
          _item = new OfflineUser(_tmpUserId,_tmpName,_tmpEmail,_tmpPhone,_tmpProfileImage,_tmpRole,_tmpIsEnabled,_tmpPermissionsJson);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getUserById(final String userId,
      final Continuation<? super OfflineUser> $completion) {
    final String _sql = "SELECT * FROM offline_users WHERE userId = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        final int _cursorIndexOfUserId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "userId");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _cursorIndexOfPhone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phone");
        final int _cursorIndexOfProfileImage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profileImage");
        final int _cursorIndexOfRole = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "role");
        final int _cursorIndexOfIsEnabled = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isEnabled");
        final int _cursorIndexOfPermissionsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "permissionsJson");
        final OfflineUser _result;
        if (_stmt.step()) {
          final String _tmpUserId;
          if (_stmt.isNull(_cursorIndexOfUserId)) {
            _tmpUserId = null;
          } else {
            _tmpUserId = _stmt.getText(_cursorIndexOfUserId);
          }
          final String _tmpName;
          if (_stmt.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_cursorIndexOfName);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_cursorIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_cursorIndexOfEmail);
          }
          final String _tmpPhone;
          if (_stmt.isNull(_cursorIndexOfPhone)) {
            _tmpPhone = null;
          } else {
            _tmpPhone = _stmt.getText(_cursorIndexOfPhone);
          }
          final String _tmpProfileImage;
          if (_stmt.isNull(_cursorIndexOfProfileImage)) {
            _tmpProfileImage = null;
          } else {
            _tmpProfileImage = _stmt.getText(_cursorIndexOfProfileImage);
          }
          final String _tmpRole;
          if (_stmt.isNull(_cursorIndexOfRole)) {
            _tmpRole = null;
          } else {
            _tmpRole = _stmt.getText(_cursorIndexOfRole);
          }
          final boolean _tmpIsEnabled;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsEnabled));
          _tmpIsEnabled = _tmp != 0;
          final String _tmpPermissionsJson;
          if (_stmt.isNull(_cursorIndexOfPermissionsJson)) {
            _tmpPermissionsJson = null;
          } else {
            _tmpPermissionsJson = _stmt.getText(_cursorIndexOfPermissionsJson);
          }
          _result = new OfflineUser(_tmpUserId,_tmpName,_tmpEmail,_tmpPhone,_tmpProfileImage,_tmpRole,_tmpIsEnabled,_tmpPermissionsJson);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final String userId, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_users WHERE userId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, userId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearUsers(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_users";
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
