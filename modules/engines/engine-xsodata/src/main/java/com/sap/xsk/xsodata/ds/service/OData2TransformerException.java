/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.service;

/**
 * The {@link OData2TransformerException} is thrown in situations when there is an issue when transforming odata file
 */
public class OData2TransformerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new transformer exception.
     */
    public OData2TransformerException() {
        super();
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param message the message
     */
    public OData2TransformerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param ex the ex
     */
    public OData2TransformerException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new transformer exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public OData2TransformerException(String message, Throwable ex) {
        super(message, ex);
    }

}
