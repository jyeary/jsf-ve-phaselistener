/*
 * Copyright 2016 Blue Lotus Software, LLC.
 * Copyright 2015-2016 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotussoftware;

import static com.bluelotussoftware.jsf.utils.JSFUtils.mapVariableToValueExpression;
import static com.bluelotussoftware.jsf.utils.JSFUtils.setValueExpressionToValue;
import javax.el.ValueExpression;
import javax.el.VariableMapper;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
public class PhaseListenerImpl implements PhaseListener {

    private static final long serialVersionUID = 2048505728914804797L;

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {

        // Set a number of values to various EL expressions.
        setValueExpressionToValue("Hello World", "#{alpha}");
        setValueExpressionToValue("Now is the time for all good men to come to the aid of their country.", "#{beta}");
        setValueExpressionToValue(true, "#{gamma}");
        setValueExpressionToValue("true", "#{delta}");
        setValueExpressionToValue(42, "#{epsilon}");
        setValueExpressionToValue("false", "#{iota}");

        // Map an EL variable to an existing ValueExpression.
        mapVariableToValueExpression("zeta", "#{alpha}");

        // Use a VariableMapper to map a value on the fly and assign it.
        VariableMapper mapper = FacesContext.getCurrentInstance().getELContext().getVariableMapper();
        ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().
                createValueExpression(FacesContext.getCurrentInstance().getELContext(),
                        "#{theta}",
                        Object.class);
        ve.setValue(FacesContext.getCurrentInstance().getELContext(), "Greek Alphabet System");
        mapper.setVariable("eta", ve);
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
