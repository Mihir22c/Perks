package org.example.project

import com.liftric.kvault.KVault
import org.example.project.db.appContext

actual fun createKVault(): KVault = KVault(appContext)