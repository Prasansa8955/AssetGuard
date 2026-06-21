package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineAssetRequest;
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
public final class RequestDao_Impl implements RequestDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineAssetRequest> __insertAdapterOfOfflineAssetRequest;

  private final EntityDeleteOrUpdateAdapter<OfflineAssetRequest> __updateAdapterOfOfflineAssetRequest;

  public RequestDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineAssetRequest = new EntityInsertAdapter<OfflineAssetRequest>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_asset_requests` (`id`,`requesterId`,`requesterName`,`assetId`,`assetName`,`requestType`,`destinationSite`,`status`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAssetRequest entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getRequesterId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getRequesterId());
        }
        if (entity.getRequesterName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRequesterName());
        }
        if (entity.getAssetId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getAssetId());
        }
        if (entity.getAssetName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getAssetName());
        }
        if (entity.getRequestType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getRequestType());
        }
        if (entity.getDestinationSite() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getDestinationSite());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getStatus());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(9, _tmp);
      }
    };
    this.__updateAdapterOfOfflineAssetRequest = new EntityDeleteOrUpdateAdapter<OfflineAssetRequest>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `offline_asset_requests` SET `id` = ?,`requesterId` = ?,`requesterName` = ?,`assetId` = ?,`assetName` = ?,`requestType` = ?,`destinationSite` = ?,`status` = ?,`isPendingSync` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAssetRequest entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getRequesterId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getRequesterId());
        }
        if (entity.getRequesterName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getRequesterName());
        }
        if (entity.getAssetId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getAssetId());
        }
        if (entity.getAssetName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getAssetName());
        }
        if (entity.getRequestType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getRequestType());
        }
        if (entity.getDestinationSite() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getDestinationSite());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getStatus());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertRequest(final OfflineAssetRequest request,
      final Continuation<? super Unit> $completion) {
    if (request == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineAssetRequest.insert(_connection, request);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateRequest(final OfflineAssetRequest request,
      final Continuation<? super Unit> $completion) {
    if (request == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOfflineAssetRequest.handle(_connection, request);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllRequests(final Continuation<? super List<OfflineAssetRequest>> $completion) {
    final String _sql = "SELECT * FROM offline_asset_requests ORDER BY id DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfRequesterId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterId");
        final int _cursorIndexOfRequesterName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterName");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfRequestType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requestType");
        final int _cursorIndexOfDestinationSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "destinationSite");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssetRequest> _result = new ArrayList<OfflineAssetRequest>();
        while (_stmt.step()) {
          final OfflineAssetRequest _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpRequesterId;
          if (_stmt.isNull(_cursorIndexOfRequesterId)) {
            _tmpRequesterId = null;
          } else {
            _tmpRequesterId = _stmt.getText(_cursorIndexOfRequesterId);
          }
          final String _tmpRequesterName;
          if (_stmt.isNull(_cursorIndexOfRequesterName)) {
            _tmpRequesterName = null;
          } else {
            _tmpRequesterName = _stmt.getText(_cursorIndexOfRequesterName);
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
          final String _tmpRequestType;
          if (_stmt.isNull(_cursorIndexOfRequestType)) {
            _tmpRequestType = null;
          } else {
            _tmpRequestType = _stmt.getText(_cursorIndexOfRequestType);
          }
          final String _tmpDestinationSite;
          if (_stmt.isNull(_cursorIndexOfDestinationSite)) {
            _tmpDestinationSite = null;
          } else {
            _tmpDestinationSite = _stmt.getText(_cursorIndexOfDestinationSite);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssetRequest(_tmpId,_tmpRequesterId,_tmpRequesterName,_tmpAssetId,_tmpAssetName,_tmpRequestType,_tmpDestinationSite,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getRequestsForEmployee(final String reqId,
      final Continuation<? super List<OfflineAssetRequest>> $completion) {
    final String _sql = "SELECT * FROM offline_asset_requests WHERE requesterId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (reqId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, reqId);
        }
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfRequesterId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterId");
        final int _cursorIndexOfRequesterName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterName");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfRequestType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requestType");
        final int _cursorIndexOfDestinationSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "destinationSite");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssetRequest> _result = new ArrayList<OfflineAssetRequest>();
        while (_stmt.step()) {
          final OfflineAssetRequest _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpRequesterId;
          if (_stmt.isNull(_cursorIndexOfRequesterId)) {
            _tmpRequesterId = null;
          } else {
            _tmpRequesterId = _stmt.getText(_cursorIndexOfRequesterId);
          }
          final String _tmpRequesterName;
          if (_stmt.isNull(_cursorIndexOfRequesterName)) {
            _tmpRequesterName = null;
          } else {
            _tmpRequesterName = _stmt.getText(_cursorIndexOfRequesterName);
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
          final String _tmpRequestType;
          if (_stmt.isNull(_cursorIndexOfRequestType)) {
            _tmpRequestType = null;
          } else {
            _tmpRequestType = _stmt.getText(_cursorIndexOfRequestType);
          }
          final String _tmpDestinationSite;
          if (_stmt.isNull(_cursorIndexOfDestinationSite)) {
            _tmpDestinationSite = null;
          } else {
            _tmpDestinationSite = _stmt.getText(_cursorIndexOfDestinationSite);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssetRequest(_tmpId,_tmpRequesterId,_tmpRequesterName,_tmpAssetId,_tmpAssetName,_tmpRequestType,_tmpDestinationSite,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getPendingRequests(
      final Continuation<? super List<OfflineAssetRequest>> $completion) {
    final String _sql = "SELECT * FROM offline_asset_requests WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfRequesterId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterId");
        final int _cursorIndexOfRequesterName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requesterName");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfRequestType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "requestType");
        final int _cursorIndexOfDestinationSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "destinationSite");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAssetRequest> _result = new ArrayList<OfflineAssetRequest>();
        while (_stmt.step()) {
          final OfflineAssetRequest _item;
          final String _tmpId;
          if (_stmt.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_cursorIndexOfId);
          }
          final String _tmpRequesterId;
          if (_stmt.isNull(_cursorIndexOfRequesterId)) {
            _tmpRequesterId = null;
          } else {
            _tmpRequesterId = _stmt.getText(_cursorIndexOfRequesterId);
          }
          final String _tmpRequesterName;
          if (_stmt.isNull(_cursorIndexOfRequesterName)) {
            _tmpRequesterName = null;
          } else {
            _tmpRequesterName = _stmt.getText(_cursorIndexOfRequesterName);
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
          final String _tmpRequestType;
          if (_stmt.isNull(_cursorIndexOfRequestType)) {
            _tmpRequestType = null;
          } else {
            _tmpRequestType = _stmt.getText(_cursorIndexOfRequestType);
          }
          final String _tmpDestinationSite;
          if (_stmt.isNull(_cursorIndexOfDestinationSite)) {
            _tmpDestinationSite = null;
          } else {
            _tmpDestinationSite = _stmt.getText(_cursorIndexOfDestinationSite);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAssetRequest(_tmpId,_tmpRequesterId,_tmpRequesterName,_tmpAssetId,_tmpAssetName,_tmpRequestType,_tmpDestinationSite,_tmpStatus,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearRequests(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_asset_requests";
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
