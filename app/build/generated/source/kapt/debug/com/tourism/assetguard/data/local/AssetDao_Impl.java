package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.tourism.assetguard.data.model.OfflineAsset;
import java.lang.Class;
import java.lang.Long;
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
public final class AssetDao_Impl implements AssetDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OfflineAsset> __insertAdapterOfOfflineAsset;

  private final EntityDeleteOrUpdateAdapter<OfflineAsset> __updateAdapterOfOfflineAsset;

  public AssetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOfflineAsset = new EntityInsertAdapter<OfflineAsset>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `offline_assets` (`id`,`name`,`description`,`category`,`serialNumber`,`barcode`,`imageUrlsJson`,`purchaseDate`,`purchasePrice`,`depreciationRate`,`status`,`condition`,`currentSite`,`locationDetails`,`quantity`,`assignedTo`,`assignedToId`,`assignedProject`,`assignedCompany`,`lastAuditedAt`,`lastLatitude`,`lastLongitude`,`warrantyExpiration`,`manualUrl`,`nextMaintenanceDate`,`isPendingSync`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAsset entity) {
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
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getDescription());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCategory());
        }
        if (entity.getSerialNumber() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getSerialNumber());
        }
        if (entity.getBarcode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getBarcode());
        }
        if (entity.getImageUrlsJson() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getImageUrlsJson());
        }
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getPurchaseDate());
        }
        statement.bindDouble(9, entity.getPurchasePrice());
        statement.bindDouble(10, entity.getDepreciationRate());
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getCondition());
        }
        if (entity.getCurrentSite() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getCurrentSite());
        }
        if (entity.getLocationDetails() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getLocationDetails());
        }
        statement.bindLong(15, entity.getQuantity());
        if (entity.getAssignedTo() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getAssignedTo());
        }
        if (entity.getAssignedToId() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getAssignedToId());
        }
        if (entity.getAssignedProject() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getAssignedProject());
        }
        if (entity.getAssignedCompany() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getAssignedCompany());
        }
        if (entity.getLastAuditedAt() == null) {
          statement.bindNull(20);
        } else {
          statement.bindLong(20, entity.getLastAuditedAt());
        }
        statement.bindDouble(21, entity.getLastLatitude());
        statement.bindDouble(22, entity.getLastLongitude());
        if (entity.getWarrantyExpiration() == null) {
          statement.bindNull(23);
        } else {
          statement.bindText(23, entity.getWarrantyExpiration());
        }
        if (entity.getManualUrl() == null) {
          statement.bindNull(24);
        } else {
          statement.bindText(24, entity.getManualUrl());
        }
        if (entity.getNextMaintenanceDate() == null) {
          statement.bindNull(25);
        } else {
          statement.bindText(25, entity.getNextMaintenanceDate());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(26, _tmp);
      }
    };
    this.__updateAdapterOfOfflineAsset = new EntityDeleteOrUpdateAdapter<OfflineAsset>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `offline_assets` SET `id` = ?,`name` = ?,`description` = ?,`category` = ?,`serialNumber` = ?,`barcode` = ?,`imageUrlsJson` = ?,`purchaseDate` = ?,`purchasePrice` = ?,`depreciationRate` = ?,`status` = ?,`condition` = ?,`currentSite` = ?,`locationDetails` = ?,`quantity` = ?,`assignedTo` = ?,`assignedToId` = ?,`assignedProject` = ?,`assignedCompany` = ?,`lastAuditedAt` = ?,`lastLatitude` = ?,`lastLongitude` = ?,`warrantyExpiration` = ?,`manualUrl` = ?,`nextMaintenanceDate` = ?,`isPendingSync` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OfflineAsset entity) {
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
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getDescription());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCategory());
        }
        if (entity.getSerialNumber() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getSerialNumber());
        }
        if (entity.getBarcode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getBarcode());
        }
        if (entity.getImageUrlsJson() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getImageUrlsJson());
        }
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getPurchaseDate());
        }
        statement.bindDouble(9, entity.getPurchasePrice());
        statement.bindDouble(10, entity.getDepreciationRate());
        if (entity.getStatus() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getStatus());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getCondition());
        }
        if (entity.getCurrentSite() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getCurrentSite());
        }
        if (entity.getLocationDetails() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getLocationDetails());
        }
        statement.bindLong(15, entity.getQuantity());
        if (entity.getAssignedTo() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getAssignedTo());
        }
        if (entity.getAssignedToId() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getAssignedToId());
        }
        if (entity.getAssignedProject() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getAssignedProject());
        }
        if (entity.getAssignedCompany() == null) {
          statement.bindNull(19);
        } else {
          statement.bindText(19, entity.getAssignedCompany());
        }
        if (entity.getLastAuditedAt() == null) {
          statement.bindNull(20);
        } else {
          statement.bindLong(20, entity.getLastAuditedAt());
        }
        statement.bindDouble(21, entity.getLastLatitude());
        statement.bindDouble(22, entity.getLastLongitude());
        if (entity.getWarrantyExpiration() == null) {
          statement.bindNull(23);
        } else {
          statement.bindText(23, entity.getWarrantyExpiration());
        }
        if (entity.getManualUrl() == null) {
          statement.bindNull(24);
        } else {
          statement.bindText(24, entity.getManualUrl());
        }
        if (entity.getNextMaintenanceDate() == null) {
          statement.bindNull(25);
        } else {
          statement.bindText(25, entity.getNextMaintenanceDate());
        }
        final int _tmp = entity.isPendingSync() ? 1 : 0;
        statement.bindLong(26, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(27);
        } else {
          statement.bindText(27, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertAssets(final List<OfflineAsset> assets,
      final Continuation<? super Unit> $completion) {
    if (assets == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineAsset.insert(_connection, assets);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object insertAsset(final OfflineAsset asset,
      final Continuation<? super Unit> $completion) {
    if (asset == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOfflineAsset.insert(_connection, asset);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateAsset(final OfflineAsset asset,
      final Continuation<? super Unit> $completion) {
    if (asset == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOfflineAsset.handle(_connection, asset);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllAssets(final Continuation<? super List<OfflineAsset>> $completion) {
    final String _sql = "SELECT * FROM offline_assets";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _cursorIndexOfSerialNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serialNumber");
        final int _cursorIndexOfBarcode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "barcode");
        final int _cursorIndexOfImageUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrlsJson");
        final int _cursorIndexOfPurchaseDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchaseDate");
        final int _cursorIndexOfPurchasePrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchasePrice");
        final int _cursorIndexOfDepreciationRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "depreciationRate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfCurrentSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentSite");
        final int _cursorIndexOfLocationDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "locationDetails");
        final int _cursorIndexOfQuantity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "quantity");
        final int _cursorIndexOfAssignedTo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedTo");
        final int _cursorIndexOfAssignedToId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedToId");
        final int _cursorIndexOfAssignedProject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedProject");
        final int _cursorIndexOfAssignedCompany = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedCompany");
        final int _cursorIndexOfLastAuditedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastAuditedAt");
        final int _cursorIndexOfLastLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLatitude");
        final int _cursorIndexOfLastLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLongitude");
        final int _cursorIndexOfWarrantyExpiration = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "warrantyExpiration");
        final int _cursorIndexOfManualUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manualUrl");
        final int _cursorIndexOfNextMaintenanceDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nextMaintenanceDate");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAsset> _result = new ArrayList<OfflineAsset>();
        while (_stmt.step()) {
          final OfflineAsset _item;
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
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_cursorIndexOfCategory);
          }
          final String _tmpSerialNumber;
          if (_stmt.isNull(_cursorIndexOfSerialNumber)) {
            _tmpSerialNumber = null;
          } else {
            _tmpSerialNumber = _stmt.getText(_cursorIndexOfSerialNumber);
          }
          final String _tmpBarcode;
          if (_stmt.isNull(_cursorIndexOfBarcode)) {
            _tmpBarcode = null;
          } else {
            _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode);
          }
          final String _tmpImageUrlsJson;
          if (_stmt.isNull(_cursorIndexOfImageUrlsJson)) {
            _tmpImageUrlsJson = null;
          } else {
            _tmpImageUrlsJson = _stmt.getText(_cursorIndexOfImageUrlsJson);
          }
          final String _tmpPurchaseDate;
          if (_stmt.isNull(_cursorIndexOfPurchaseDate)) {
            _tmpPurchaseDate = null;
          } else {
            _tmpPurchaseDate = _stmt.getText(_cursorIndexOfPurchaseDate);
          }
          final double _tmpPurchasePrice;
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice);
          final double _tmpDepreciationRate;
          _tmpDepreciationRate = _stmt.getDouble(_cursorIndexOfDepreciationRate);
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpCurrentSite;
          if (_stmt.isNull(_cursorIndexOfCurrentSite)) {
            _tmpCurrentSite = null;
          } else {
            _tmpCurrentSite = _stmt.getText(_cursorIndexOfCurrentSite);
          }
          final String _tmpLocationDetails;
          if (_stmt.isNull(_cursorIndexOfLocationDetails)) {
            _tmpLocationDetails = null;
          } else {
            _tmpLocationDetails = _stmt.getText(_cursorIndexOfLocationDetails);
          }
          final int _tmpQuantity;
          _tmpQuantity = (int) (_stmt.getLong(_cursorIndexOfQuantity));
          final String _tmpAssignedTo;
          if (_stmt.isNull(_cursorIndexOfAssignedTo)) {
            _tmpAssignedTo = null;
          } else {
            _tmpAssignedTo = _stmt.getText(_cursorIndexOfAssignedTo);
          }
          final String _tmpAssignedToId;
          if (_stmt.isNull(_cursorIndexOfAssignedToId)) {
            _tmpAssignedToId = null;
          } else {
            _tmpAssignedToId = _stmt.getText(_cursorIndexOfAssignedToId);
          }
          final String _tmpAssignedProject;
          if (_stmt.isNull(_cursorIndexOfAssignedProject)) {
            _tmpAssignedProject = null;
          } else {
            _tmpAssignedProject = _stmt.getText(_cursorIndexOfAssignedProject);
          }
          final String _tmpAssignedCompany;
          if (_stmt.isNull(_cursorIndexOfAssignedCompany)) {
            _tmpAssignedCompany = null;
          } else {
            _tmpAssignedCompany = _stmt.getText(_cursorIndexOfAssignedCompany);
          }
          final Long _tmpLastAuditedAt;
          if (_stmt.isNull(_cursorIndexOfLastAuditedAt)) {
            _tmpLastAuditedAt = null;
          } else {
            _tmpLastAuditedAt = _stmt.getLong(_cursorIndexOfLastAuditedAt);
          }
          final double _tmpLastLatitude;
          _tmpLastLatitude = _stmt.getDouble(_cursorIndexOfLastLatitude);
          final double _tmpLastLongitude;
          _tmpLastLongitude = _stmt.getDouble(_cursorIndexOfLastLongitude);
          final String _tmpWarrantyExpiration;
          if (_stmt.isNull(_cursorIndexOfWarrantyExpiration)) {
            _tmpWarrantyExpiration = null;
          } else {
            _tmpWarrantyExpiration = _stmt.getText(_cursorIndexOfWarrantyExpiration);
          }
          final String _tmpManualUrl;
          if (_stmt.isNull(_cursorIndexOfManualUrl)) {
            _tmpManualUrl = null;
          } else {
            _tmpManualUrl = _stmt.getText(_cursorIndexOfManualUrl);
          }
          final String _tmpNextMaintenanceDate;
          if (_stmt.isNull(_cursorIndexOfNextMaintenanceDate)) {
            _tmpNextMaintenanceDate = null;
          } else {
            _tmpNextMaintenanceDate = _stmt.getText(_cursorIndexOfNextMaintenanceDate);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAsset(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpSerialNumber,_tmpBarcode,_tmpImageUrlsJson,_tmpPurchaseDate,_tmpPurchasePrice,_tmpDepreciationRate,_tmpStatus,_tmpCondition,_tmpCurrentSite,_tmpLocationDetails,_tmpQuantity,_tmpAssignedTo,_tmpAssignedToId,_tmpAssignedProject,_tmpAssignedCompany,_tmpLastAuditedAt,_tmpLastLatitude,_tmpLastLongitude,_tmpWarrantyExpiration,_tmpManualUrl,_tmpNextMaintenanceDate,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAssetById(final String assetId,
      final Continuation<? super OfflineAsset> $completion) {
    final String _sql = "SELECT * FROM offline_assets WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (assetId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, assetId);
        }
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _cursorIndexOfSerialNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serialNumber");
        final int _cursorIndexOfBarcode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "barcode");
        final int _cursorIndexOfImageUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrlsJson");
        final int _cursorIndexOfPurchaseDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchaseDate");
        final int _cursorIndexOfPurchasePrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchasePrice");
        final int _cursorIndexOfDepreciationRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "depreciationRate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfCurrentSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentSite");
        final int _cursorIndexOfLocationDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "locationDetails");
        final int _cursorIndexOfQuantity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "quantity");
        final int _cursorIndexOfAssignedTo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedTo");
        final int _cursorIndexOfAssignedToId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedToId");
        final int _cursorIndexOfAssignedProject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedProject");
        final int _cursorIndexOfAssignedCompany = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedCompany");
        final int _cursorIndexOfLastAuditedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastAuditedAt");
        final int _cursorIndexOfLastLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLatitude");
        final int _cursorIndexOfLastLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLongitude");
        final int _cursorIndexOfWarrantyExpiration = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "warrantyExpiration");
        final int _cursorIndexOfManualUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manualUrl");
        final int _cursorIndexOfNextMaintenanceDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nextMaintenanceDate");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final OfflineAsset _result;
        if (_stmt.step()) {
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
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_cursorIndexOfCategory);
          }
          final String _tmpSerialNumber;
          if (_stmt.isNull(_cursorIndexOfSerialNumber)) {
            _tmpSerialNumber = null;
          } else {
            _tmpSerialNumber = _stmt.getText(_cursorIndexOfSerialNumber);
          }
          final String _tmpBarcode;
          if (_stmt.isNull(_cursorIndexOfBarcode)) {
            _tmpBarcode = null;
          } else {
            _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode);
          }
          final String _tmpImageUrlsJson;
          if (_stmt.isNull(_cursorIndexOfImageUrlsJson)) {
            _tmpImageUrlsJson = null;
          } else {
            _tmpImageUrlsJson = _stmt.getText(_cursorIndexOfImageUrlsJson);
          }
          final String _tmpPurchaseDate;
          if (_stmt.isNull(_cursorIndexOfPurchaseDate)) {
            _tmpPurchaseDate = null;
          } else {
            _tmpPurchaseDate = _stmt.getText(_cursorIndexOfPurchaseDate);
          }
          final double _tmpPurchasePrice;
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice);
          final double _tmpDepreciationRate;
          _tmpDepreciationRate = _stmt.getDouble(_cursorIndexOfDepreciationRate);
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpCurrentSite;
          if (_stmt.isNull(_cursorIndexOfCurrentSite)) {
            _tmpCurrentSite = null;
          } else {
            _tmpCurrentSite = _stmt.getText(_cursorIndexOfCurrentSite);
          }
          final String _tmpLocationDetails;
          if (_stmt.isNull(_cursorIndexOfLocationDetails)) {
            _tmpLocationDetails = null;
          } else {
            _tmpLocationDetails = _stmt.getText(_cursorIndexOfLocationDetails);
          }
          final int _tmpQuantity;
          _tmpQuantity = (int) (_stmt.getLong(_cursorIndexOfQuantity));
          final String _tmpAssignedTo;
          if (_stmt.isNull(_cursorIndexOfAssignedTo)) {
            _tmpAssignedTo = null;
          } else {
            _tmpAssignedTo = _stmt.getText(_cursorIndexOfAssignedTo);
          }
          final String _tmpAssignedToId;
          if (_stmt.isNull(_cursorIndexOfAssignedToId)) {
            _tmpAssignedToId = null;
          } else {
            _tmpAssignedToId = _stmt.getText(_cursorIndexOfAssignedToId);
          }
          final String _tmpAssignedProject;
          if (_stmt.isNull(_cursorIndexOfAssignedProject)) {
            _tmpAssignedProject = null;
          } else {
            _tmpAssignedProject = _stmt.getText(_cursorIndexOfAssignedProject);
          }
          final String _tmpAssignedCompany;
          if (_stmt.isNull(_cursorIndexOfAssignedCompany)) {
            _tmpAssignedCompany = null;
          } else {
            _tmpAssignedCompany = _stmt.getText(_cursorIndexOfAssignedCompany);
          }
          final Long _tmpLastAuditedAt;
          if (_stmt.isNull(_cursorIndexOfLastAuditedAt)) {
            _tmpLastAuditedAt = null;
          } else {
            _tmpLastAuditedAt = _stmt.getLong(_cursorIndexOfLastAuditedAt);
          }
          final double _tmpLastLatitude;
          _tmpLastLatitude = _stmt.getDouble(_cursorIndexOfLastLatitude);
          final double _tmpLastLongitude;
          _tmpLastLongitude = _stmt.getDouble(_cursorIndexOfLastLongitude);
          final String _tmpWarrantyExpiration;
          if (_stmt.isNull(_cursorIndexOfWarrantyExpiration)) {
            _tmpWarrantyExpiration = null;
          } else {
            _tmpWarrantyExpiration = _stmt.getText(_cursorIndexOfWarrantyExpiration);
          }
          final String _tmpManualUrl;
          if (_stmt.isNull(_cursorIndexOfManualUrl)) {
            _tmpManualUrl = null;
          } else {
            _tmpManualUrl = _stmt.getText(_cursorIndexOfManualUrl);
          }
          final String _tmpNextMaintenanceDate;
          if (_stmt.isNull(_cursorIndexOfNextMaintenanceDate)) {
            _tmpNextMaintenanceDate = null;
          } else {
            _tmpNextMaintenanceDate = _stmt.getText(_cursorIndexOfNextMaintenanceDate);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _result = new OfflineAsset(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpSerialNumber,_tmpBarcode,_tmpImageUrlsJson,_tmpPurchaseDate,_tmpPurchasePrice,_tmpDepreciationRate,_tmpStatus,_tmpCondition,_tmpCurrentSite,_tmpLocationDetails,_tmpQuantity,_tmpAssignedTo,_tmpAssignedToId,_tmpAssignedProject,_tmpAssignedCompany,_tmpLastAuditedAt,_tmpLastLatitude,_tmpLastLongitude,_tmpWarrantyExpiration,_tmpManualUrl,_tmpNextMaintenanceDate,_tmpIsPendingSync);
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
  public Object getAssetByBarcode(final String barcode,
      final Continuation<? super OfflineAsset> $completion) {
    final String _sql = "SELECT * FROM offline_assets WHERE barcode = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (barcode == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, barcode);
        }
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _cursorIndexOfSerialNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serialNumber");
        final int _cursorIndexOfBarcode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "barcode");
        final int _cursorIndexOfImageUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrlsJson");
        final int _cursorIndexOfPurchaseDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchaseDate");
        final int _cursorIndexOfPurchasePrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchasePrice");
        final int _cursorIndexOfDepreciationRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "depreciationRate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfCurrentSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentSite");
        final int _cursorIndexOfLocationDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "locationDetails");
        final int _cursorIndexOfQuantity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "quantity");
        final int _cursorIndexOfAssignedTo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedTo");
        final int _cursorIndexOfAssignedToId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedToId");
        final int _cursorIndexOfAssignedProject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedProject");
        final int _cursorIndexOfAssignedCompany = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedCompany");
        final int _cursorIndexOfLastAuditedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastAuditedAt");
        final int _cursorIndexOfLastLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLatitude");
        final int _cursorIndexOfLastLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLongitude");
        final int _cursorIndexOfWarrantyExpiration = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "warrantyExpiration");
        final int _cursorIndexOfManualUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manualUrl");
        final int _cursorIndexOfNextMaintenanceDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nextMaintenanceDate");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final OfflineAsset _result;
        if (_stmt.step()) {
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
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_cursorIndexOfCategory);
          }
          final String _tmpSerialNumber;
          if (_stmt.isNull(_cursorIndexOfSerialNumber)) {
            _tmpSerialNumber = null;
          } else {
            _tmpSerialNumber = _stmt.getText(_cursorIndexOfSerialNumber);
          }
          final String _tmpBarcode;
          if (_stmt.isNull(_cursorIndexOfBarcode)) {
            _tmpBarcode = null;
          } else {
            _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode);
          }
          final String _tmpImageUrlsJson;
          if (_stmt.isNull(_cursorIndexOfImageUrlsJson)) {
            _tmpImageUrlsJson = null;
          } else {
            _tmpImageUrlsJson = _stmt.getText(_cursorIndexOfImageUrlsJson);
          }
          final String _tmpPurchaseDate;
          if (_stmt.isNull(_cursorIndexOfPurchaseDate)) {
            _tmpPurchaseDate = null;
          } else {
            _tmpPurchaseDate = _stmt.getText(_cursorIndexOfPurchaseDate);
          }
          final double _tmpPurchasePrice;
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice);
          final double _tmpDepreciationRate;
          _tmpDepreciationRate = _stmt.getDouble(_cursorIndexOfDepreciationRate);
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpCurrentSite;
          if (_stmt.isNull(_cursorIndexOfCurrentSite)) {
            _tmpCurrentSite = null;
          } else {
            _tmpCurrentSite = _stmt.getText(_cursorIndexOfCurrentSite);
          }
          final String _tmpLocationDetails;
          if (_stmt.isNull(_cursorIndexOfLocationDetails)) {
            _tmpLocationDetails = null;
          } else {
            _tmpLocationDetails = _stmt.getText(_cursorIndexOfLocationDetails);
          }
          final int _tmpQuantity;
          _tmpQuantity = (int) (_stmt.getLong(_cursorIndexOfQuantity));
          final String _tmpAssignedTo;
          if (_stmt.isNull(_cursorIndexOfAssignedTo)) {
            _tmpAssignedTo = null;
          } else {
            _tmpAssignedTo = _stmt.getText(_cursorIndexOfAssignedTo);
          }
          final String _tmpAssignedToId;
          if (_stmt.isNull(_cursorIndexOfAssignedToId)) {
            _tmpAssignedToId = null;
          } else {
            _tmpAssignedToId = _stmt.getText(_cursorIndexOfAssignedToId);
          }
          final String _tmpAssignedProject;
          if (_stmt.isNull(_cursorIndexOfAssignedProject)) {
            _tmpAssignedProject = null;
          } else {
            _tmpAssignedProject = _stmt.getText(_cursorIndexOfAssignedProject);
          }
          final String _tmpAssignedCompany;
          if (_stmt.isNull(_cursorIndexOfAssignedCompany)) {
            _tmpAssignedCompany = null;
          } else {
            _tmpAssignedCompany = _stmt.getText(_cursorIndexOfAssignedCompany);
          }
          final Long _tmpLastAuditedAt;
          if (_stmt.isNull(_cursorIndexOfLastAuditedAt)) {
            _tmpLastAuditedAt = null;
          } else {
            _tmpLastAuditedAt = _stmt.getLong(_cursorIndexOfLastAuditedAt);
          }
          final double _tmpLastLatitude;
          _tmpLastLatitude = _stmt.getDouble(_cursorIndexOfLastLatitude);
          final double _tmpLastLongitude;
          _tmpLastLongitude = _stmt.getDouble(_cursorIndexOfLastLongitude);
          final String _tmpWarrantyExpiration;
          if (_stmt.isNull(_cursorIndexOfWarrantyExpiration)) {
            _tmpWarrantyExpiration = null;
          } else {
            _tmpWarrantyExpiration = _stmt.getText(_cursorIndexOfWarrantyExpiration);
          }
          final String _tmpManualUrl;
          if (_stmt.isNull(_cursorIndexOfManualUrl)) {
            _tmpManualUrl = null;
          } else {
            _tmpManualUrl = _stmt.getText(_cursorIndexOfManualUrl);
          }
          final String _tmpNextMaintenanceDate;
          if (_stmt.isNull(_cursorIndexOfNextMaintenanceDate)) {
            _tmpNextMaintenanceDate = null;
          } else {
            _tmpNextMaintenanceDate = _stmt.getText(_cursorIndexOfNextMaintenanceDate);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _result = new OfflineAsset(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpSerialNumber,_tmpBarcode,_tmpImageUrlsJson,_tmpPurchaseDate,_tmpPurchasePrice,_tmpDepreciationRate,_tmpStatus,_tmpCondition,_tmpCurrentSite,_tmpLocationDetails,_tmpQuantity,_tmpAssignedTo,_tmpAssignedToId,_tmpAssignedProject,_tmpAssignedCompany,_tmpLastAuditedAt,_tmpLastLatitude,_tmpLastLongitude,_tmpWarrantyExpiration,_tmpManualUrl,_tmpNextMaintenanceDate,_tmpIsPendingSync);
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
  public Object getPendingAssets(final Continuation<? super List<OfflineAsset>> $completion) {
    final String _sql = "SELECT * FROM offline_assets WHERE isPendingSync = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _cursorIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _cursorIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _cursorIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _cursorIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _cursorIndexOfSerialNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "serialNumber");
        final int _cursorIndexOfBarcode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "barcode");
        final int _cursorIndexOfImageUrlsJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrlsJson");
        final int _cursorIndexOfPurchaseDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchaseDate");
        final int _cursorIndexOfPurchasePrice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "purchasePrice");
        final int _cursorIndexOfDepreciationRate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "depreciationRate");
        final int _cursorIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _cursorIndexOfCondition = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "condition");
        final int _cursorIndexOfCurrentSite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentSite");
        final int _cursorIndexOfLocationDetails = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "locationDetails");
        final int _cursorIndexOfQuantity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "quantity");
        final int _cursorIndexOfAssignedTo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedTo");
        final int _cursorIndexOfAssignedToId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedToId");
        final int _cursorIndexOfAssignedProject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedProject");
        final int _cursorIndexOfAssignedCompany = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "assignedCompany");
        final int _cursorIndexOfLastAuditedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastAuditedAt");
        final int _cursorIndexOfLastLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLatitude");
        final int _cursorIndexOfLastLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLongitude");
        final int _cursorIndexOfWarrantyExpiration = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "warrantyExpiration");
        final int _cursorIndexOfManualUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "manualUrl");
        final int _cursorIndexOfNextMaintenanceDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nextMaintenanceDate");
        final int _cursorIndexOfIsPendingSync = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPendingSync");
        final List<OfflineAsset> _result = new ArrayList<OfflineAsset>();
        while (_stmt.step()) {
          final OfflineAsset _item;
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
          final String _tmpDescription;
          if (_stmt.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_cursorIndexOfDescription);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_cursorIndexOfCategory);
          }
          final String _tmpSerialNumber;
          if (_stmt.isNull(_cursorIndexOfSerialNumber)) {
            _tmpSerialNumber = null;
          } else {
            _tmpSerialNumber = _stmt.getText(_cursorIndexOfSerialNumber);
          }
          final String _tmpBarcode;
          if (_stmt.isNull(_cursorIndexOfBarcode)) {
            _tmpBarcode = null;
          } else {
            _tmpBarcode = _stmt.getText(_cursorIndexOfBarcode);
          }
          final String _tmpImageUrlsJson;
          if (_stmt.isNull(_cursorIndexOfImageUrlsJson)) {
            _tmpImageUrlsJson = null;
          } else {
            _tmpImageUrlsJson = _stmt.getText(_cursorIndexOfImageUrlsJson);
          }
          final String _tmpPurchaseDate;
          if (_stmt.isNull(_cursorIndexOfPurchaseDate)) {
            _tmpPurchaseDate = null;
          } else {
            _tmpPurchaseDate = _stmt.getText(_cursorIndexOfPurchaseDate);
          }
          final double _tmpPurchasePrice;
          _tmpPurchasePrice = _stmt.getDouble(_cursorIndexOfPurchasePrice);
          final double _tmpDepreciationRate;
          _tmpDepreciationRate = _stmt.getDouble(_cursorIndexOfDepreciationRate);
          final String _tmpStatus;
          if (_stmt.isNull(_cursorIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_cursorIndexOfStatus);
          }
          final String _tmpCondition;
          if (_stmt.isNull(_cursorIndexOfCondition)) {
            _tmpCondition = null;
          } else {
            _tmpCondition = _stmt.getText(_cursorIndexOfCondition);
          }
          final String _tmpCurrentSite;
          if (_stmt.isNull(_cursorIndexOfCurrentSite)) {
            _tmpCurrentSite = null;
          } else {
            _tmpCurrentSite = _stmt.getText(_cursorIndexOfCurrentSite);
          }
          final String _tmpLocationDetails;
          if (_stmt.isNull(_cursorIndexOfLocationDetails)) {
            _tmpLocationDetails = null;
          } else {
            _tmpLocationDetails = _stmt.getText(_cursorIndexOfLocationDetails);
          }
          final int _tmpQuantity;
          _tmpQuantity = (int) (_stmt.getLong(_cursorIndexOfQuantity));
          final String _tmpAssignedTo;
          if (_stmt.isNull(_cursorIndexOfAssignedTo)) {
            _tmpAssignedTo = null;
          } else {
            _tmpAssignedTo = _stmt.getText(_cursorIndexOfAssignedTo);
          }
          final String _tmpAssignedToId;
          if (_stmt.isNull(_cursorIndexOfAssignedToId)) {
            _tmpAssignedToId = null;
          } else {
            _tmpAssignedToId = _stmt.getText(_cursorIndexOfAssignedToId);
          }
          final String _tmpAssignedProject;
          if (_stmt.isNull(_cursorIndexOfAssignedProject)) {
            _tmpAssignedProject = null;
          } else {
            _tmpAssignedProject = _stmt.getText(_cursorIndexOfAssignedProject);
          }
          final String _tmpAssignedCompany;
          if (_stmt.isNull(_cursorIndexOfAssignedCompany)) {
            _tmpAssignedCompany = null;
          } else {
            _tmpAssignedCompany = _stmt.getText(_cursorIndexOfAssignedCompany);
          }
          final Long _tmpLastAuditedAt;
          if (_stmt.isNull(_cursorIndexOfLastAuditedAt)) {
            _tmpLastAuditedAt = null;
          } else {
            _tmpLastAuditedAt = _stmt.getLong(_cursorIndexOfLastAuditedAt);
          }
          final double _tmpLastLatitude;
          _tmpLastLatitude = _stmt.getDouble(_cursorIndexOfLastLatitude);
          final double _tmpLastLongitude;
          _tmpLastLongitude = _stmt.getDouble(_cursorIndexOfLastLongitude);
          final String _tmpWarrantyExpiration;
          if (_stmt.isNull(_cursorIndexOfWarrantyExpiration)) {
            _tmpWarrantyExpiration = null;
          } else {
            _tmpWarrantyExpiration = _stmt.getText(_cursorIndexOfWarrantyExpiration);
          }
          final String _tmpManualUrl;
          if (_stmt.isNull(_cursorIndexOfManualUrl)) {
            _tmpManualUrl = null;
          } else {
            _tmpManualUrl = _stmt.getText(_cursorIndexOfManualUrl);
          }
          final String _tmpNextMaintenanceDate;
          if (_stmt.isNull(_cursorIndexOfNextMaintenanceDate)) {
            _tmpNextMaintenanceDate = null;
          } else {
            _tmpNextMaintenanceDate = _stmt.getText(_cursorIndexOfNextMaintenanceDate);
          }
          final boolean _tmpIsPendingSync;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_cursorIndexOfIsPendingSync));
          _tmpIsPendingSync = _tmp != 0;
          _item = new OfflineAsset(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpSerialNumber,_tmpBarcode,_tmpImageUrlsJson,_tmpPurchaseDate,_tmpPurchasePrice,_tmpDepreciationRate,_tmpStatus,_tmpCondition,_tmpCurrentSite,_tmpLocationDetails,_tmpQuantity,_tmpAssignedTo,_tmpAssignedToId,_tmpAssignedProject,_tmpAssignedCompany,_tmpLastAuditedAt,_tmpLastLatitude,_tmpLastLongitude,_tmpWarrantyExpiration,_tmpManualUrl,_tmpNextMaintenanceDate,_tmpIsPendingSync);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAsset(final String assetId, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_assets WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (assetId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, assetId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearAssets(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM offline_assets";
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
