package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineMaintenanceRequest;
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
public final class MaintenanceDao_Impl implements MaintenanceDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineMaintenanceRequest> __insertAdapterOfOfflineMaintenanceRequest;

  private final EntityDeleteOrUpdateAdapter<OfflineMaintenanceRequest> __updateAdapterOfOfflineMaintenanceRequest;

  public MaintenanceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineMaintenanceRequest = new EntityInsertAdapter<OfflineMaintenanceRequest>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_maintenance_requests` (`id`,`assetId`,`assetName`,`reporterName`,`description`,`breakdownType`,`scheduledDate`,`completionDate`,`repairCost`,`notes`,`status`,`damagePhotoUrlsJson`,`mechanicName`,`partsReplaced`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineMaintenanceRequest entity) {
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
        if (entity.getReporterName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getReporterName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescription());
        }
        if (entity.getBreakdownType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getBreakdownType());
        }
        if (entity.getScheduledDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getScheduledDate());
        }
        if (entity.getCompletionDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getCompletionDate());
        }
        statement.bindDouble(9, entity.getRepairCost());
        if (entity.getNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getNotes());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        if (entity.getDamagePhotoUrlsJson() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getDamagePhotoUrlsJson());
        }
        if (entity.getMechanicName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getMechanicName());
        }
        if (entity.getPartsReplaced() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getPartsReplaced());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(15, _tmp);
      }
    };
    this.__updateAdapterOfOfflineMaintenanceRequest = new EntityDeleteOrUpdateAdapter<OfflineMaintenanceRequest>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `offline_maintenance_requests` SET `id` = ?,`assetId` = ?,`assetName` = ?,`reporterName` = ?,`description` = ?,`breakdownType` = ?,`scheduledDate` = ?,`completionDate` = ?,`repairCost` = ?,`notes` = ?,`status` = ?,`damagePhotoUrlsJson` = ?,`mechanicName` = ?,`partsReplaced` = ?,`isPendingSync` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineMaintenanceRequest entity) {
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
        if (entity.getReporterName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getReporterName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescription());
        }
        if (entity.getBreakdownType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getBreakdownType());
        }
        if (entity.getScheduledDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getScheduledDate());
        }
        if (entity.getCompletionDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getCompletionDate());
        }
        statement.bindDouble(9, entity.getRepairCost());
        if (entity.getNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getNotes());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        if (entity.getDamagePhotoUrlsJson() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getDamagePhotoUrlsJson());
        }
        if (entity.getMechanicName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getMechanicName());
        }
        if (entity.getPartsReplaced() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getPartsReplaced());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(15, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertRequest(final OfflineMaintenanceRequest request,
      final Continuation<? super Unit> $completion) {
    if (request == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineMaintenanceRequest.insert(_connection, request);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateRequest(final OfflineMaintenanceRequest request,
      final Continuation<? super Unit> $completion) {
    if (request == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOfflineMaintenanceRequest.handle(_connection, request);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllRequests(
      final Continuation<? super List<OfflineMaintenanceRequest>> $completion) {
    final String _sql = "SELECT * FROM offline_maintenance_requests ORDER BY scheduledDate DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfReporterName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reporterName");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfBreakdownType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "breakdownType");
        final int _cursorIndexOfScheduledDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scheduledDate");
        final int _cursorIndexOfCompletionDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completionDate");
        final int _cursorIndexOfRepairCost = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "repairCost");
        final int _cursorIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfDamagePhotoUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "damagePhotoUrlsJson");
        final int _cursorIndexOfMechanicName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mechanicName");
        final int _cursorIndexOfPartsReplaced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "partsReplaced");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineMaintenanceRequest> _result = new ArrayList<OfflineMaintenanceRequest>();
        while (_stmt.step()) {
          final OfflineMaintenanceRequest _item;
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
          final String _tmpReporterName;
          if (_stmt.isNull(_cursorIndexOfReporterName)) {
            _tmpReporterName = null;
          } else {
            _tmpReporterName = _stmt.getText(_cursorIndexOfReporterName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpBreakdownType;
          if (_stmt.isNull(_cursorIndexOfBreakdownType)) {
            _tmpBreakdownType = null;
          } else {
            _tmpBreakdownType = _stmt.getText(_cursorIndexOfBreakdownType);
          }
          final String _tmpScheduledDate;
          if (_stmt.isNull(_cursorIndexOfScheduledDate)) {
            _tmpScheduledDate = null;
          } else {
            _tmpScheduledDate = _stmt.getText(_cursorIndexOfScheduledDate);
          }
          final String _tmpCompletionDate;
          if (_stmt.isNull(_cursorIndexOfCompletionDate)) {
            _tmpCompletionDate = null;
          } else {
            _tmpCompletionDate = _stmt.getText(_cursorIndexOfCompletionDate);
          }
          final double _tmpRepairCost;
          _tmpRepairCost = _stmt.getDouble(_cursorIndexOfRepairCost);
          final String _tmpNotes;
          if (_stmt.isNull(_cursorIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_cursorIndexOfNotes);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpDamagePhotoUrlsJson;
          if (_stmt.isNull(_cursorIndexOfDamagePhotoUrlsJson)) {
            _tmpDamagePhotoUrlsJson = null;
          } else {
            _tmpDamagePhotoUrlsJson = _stmt.getText(_cursorIndexOfDamagePhotoUrlsJson);
          }
          final String _tmpMechanicName;
          if (_stmt.isNull(_cursorIndexOfMechanicName)) {
            _tmpMechanicName = null;
          } else {
            _tmpMechanicName = _stmt.getText(_cursorIndexOfMechanicName);
          }
          final String _tmpPartsReplaced;
          if (_stmt.isNull(_cursorIndexOfPartsReplaced)) {
            _tmpPartsReplaced = null;
          } else {
            _tmpPartsReplaced = _stmt.getText(_cursorIndexOfPartsReplaced);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineMaintenanceRequest(_tmpId,_tmpAssetId,_tmpAssetName,_tmpReporterName,_tmpDescription,_tmpBreakdownType,_tmpScheduledDate,_tmpCompletionDate,_tmpRepairCost,_tmpNotes,_tmpStatus,_tmpDamagePhotoUrlsJson,_tmpMechanicName,_tmpPartsReplaced,_tmpIsPendingSync);
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
      final Continuation<? super List<OfflineMaintenanceRequest>> $completion) {
    final String _sql = "SELECT * FROM offline_maintenance_requests WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfAssetId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetId");
        final int _cursorIndexOfAssetName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assetName");
        final int _cursorIndexOfReporterName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reporterName");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfBreakdownType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "breakdownType");
        final int _cursorIndexOfScheduledDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "scheduledDate");
        final int _cursorIndexOfCompletionDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "completionDate");
        final int _cursorIndexOfRepairCost = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "repairCost");
        final int _cursorIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfDamagePhotoUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "damagePhotoUrlsJson");
        final int _cursorIndexOfMechanicName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mechanicName");
        final int _cursorIndexOfPartsReplaced = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "partsReplaced");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineMaintenanceRequest> _result = new ArrayList<OfflineMaintenanceRequest>();
        while (_stmt.step()) {
          final OfflineMaintenanceRequest _item;
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
          final String _tmpReporterName;
          if (_stmt.isNull(_cursorIndexOfReporterName)) {
            _tmpReporterName = null;
          } else {
            _tmpReporterName = _stmt.getText(_cursorIndexOfReporterName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpBreakdownType;
          if (_stmt.isNull(_cursorIndexOfBreakdownType)) {
            _tmpBreakdownType = null;
          } else {
            _tmpBreakdownType = _stmt.getText(_cursorIndexOfBreakdownType);
          }
          final String _tmpScheduledDate;
          if (_stmt.isNull(_cursorIndexOfScheduledDate)) {
            _tmpScheduledDate = null;
          } else {
            _tmpScheduledDate = _stmt.getText(_cursorIndexOfScheduledDate);
          }
          final String _tmpCompletionDate;
          if (_stmt.isNull(_cursorIndexOfCompletionDate)) {
            _tmpCompletionDate = null;
          } else {
            _tmpCompletionDate = _stmt.getText(_cursorIndexOfCompletionDate);
          }
          final double _tmpRepairCost;
          _tmpRepairCost = _stmt.getDouble(_cursorIndexOfRepairCost);
          final String _tmpNotes;
          if (_stmt.isNull(_cursorIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_cursorIndexOfNotes);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpDamagePhotoUrlsJson;
          if (_stmt.isNull(_cursorIndexOfDamagePhotoUrlsJson)) {
            _tmpDamagePhotoUrlsJson = null;
          } else {
            _tmpDamagePhotoUrlsJson = _stmt.getText(_cursorIndexOfDamagePhotoUrlsJson);
          }
          final String _tmpMechanicName;
          if (_stmt.isNull(_cursorIndexOfMechanicName)) {
            _tmpMechanicName = null;
          } else {
            _tmpMechanicName = _stmt.getText(_cursorIndexOfMechanicName);
          }
          final String _tmpPartsReplaced;
          if (_stmt.isNull(_cursorIndexOfPartsReplaced)) {
            _tmpPartsReplaced = null;
          } else {
            _tmpPartsReplaced = _stmt.getText(_cursorIndexOfPartsReplaced);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineMaintenanceRequest(_tmpId,_tmpAssetId,_tmpAssetName,_tmpReporterName,_tmpDescription,_tmpBreakdownType,_tmpScheduledDate,_tmpCompletionDate,_tmpRepairCost,_tmpNotes,_tmpStatus,_tmpDamagePhotoUrlsJson,_tmpMechanicName,_tmpPartsReplaced,_tmpIsPendingSync);
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
    final String _sql = "DELETE FROM offline_maintenance_requests";
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
