package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineSite;
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
public final class SiteDao_Impl implements SiteDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineSite> __insertAdapterOfOfflineSite;

  public SiteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineSite = new EntityInsertAdapter<OfflineSite>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_sites` (`id`,`name`,`address`,`latitude`,`longitude`,`radiusMeters`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineSite entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getAddress());
        }
        statement.bindDouble(4, entity.getLatitude());
        statement.bindDouble(5, entity.getLongitude());
        statement.bindDouble(6, entity.getRadiusMeters());
      }
    };
  }

  @Override
  public Object insertSite(final OfflineSite site, final Continuation<? super Unit> $completion) {
    if (site == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineSite.insert(_connection, site);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllSites(final Continuation<? super List<OfflineSite>> $completion) {
    final String _sql = "SELECT * FROM offline_sites";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
        final int _cursorIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
        final int _cursorIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
        final int _cursorIndexOfRadiusMeters = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "radiusMeters");
        final List<OfflineSite> _result = new ArrayList<OfflineSite>();
        while (_stmt.step()) {
          final OfflineSite _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_cursorIndexOfName);
          }
          final String _tmpAddress;
          if (_stmt.isNull(_cursorIndexOfAddress)) {
            _tmpAddress = null;
          } else {
            _tmpAddress = _stmt.getText(_cursorIndexOfAddress);
          }
          final double _tmpLatitude;
          _tmpLatitude = _stmt.getDouble(_cursorIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _stmt.getDouble(_cursorIndexOfLongitude);
          final double _tmpRadiusMeters;
          _tmpRadiusMeters = _stmt.getDouble(_cursorIndexOfRadiusMeters);
          _item = new OfflineSite(_tmpId,_tmpName,_tmpAddress,_tmpLatitude,_tmpLongitude,_tmpRadiusMeters);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteSite(final String siteId, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_sites WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (siteId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, siteId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearSites(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_sites";
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
