/*
 * Copyright 2018 Heiko Scherrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openwms.core.values;

import java.util.List;

/**
 * A BaseUnit is the type definition of an <code>Measurable</code>. Each BaseUnit defines a base <code>Measurable</code>. For example the
 * BaseUnit of weights can define grams (G), kilograms (KG) or tons (T) and 1G is the base unit.
 *
 * @param <T> Concrete type of BaseUnit
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @GlossaryTerm
 */
public interface BaseUnit<T extends BaseUnit<T>> {

    /**
     * Return all sub types of the <code>UnitType</code>.
     *
     * @param <T> Type definition of all concrete Units
     * @return a list of sub types
     */
    List<T> getAll();

    /**
     * Return the base unit type of the <code>UnitType</code>.
     *
     * @param <T> Type definition of all concrete Units
     * @return The base unit type
     */
    T getBaseUnit();
}