package com.appiwedia.apps.android.fdjcompose.common

abstract class BaseDomainMapper<DTO, DOMAIN> {
    abstract fun toDomain(dto: DTO): DOMAIN
}