/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.rescheduleloan.domain;

import java.math.MathContext;

import org.mifosplatform.organisation.monetary.domain.ApplicationCurrency;
import org.mifosplatform.portfolio.calendar.domain.Calendar;
import org.mifosplatform.portfolio.calendar.domain.CalendarInstance;
import org.mifosplatform.portfolio.floatingrates.data.FloatingRateDTO;
import org.mifosplatform.portfolio.loanaccount.data.HolidayDetailDTO;
import org.mifosplatform.portfolio.loanaccount.loanschedule.domain.DecliningBalanceInterestLoanScheduleGenerator;
import org.mifosplatform.portfolio.loanaccount.loanschedule.domain.FlatInterestLoanScheduleGenerator;
import org.mifosplatform.portfolio.loanproduct.domain.InterestMethod;

public class DefaultLoanReschedulerFactory implements LoanReschedulerFactory {

    @Override
    public LoanRescheduleModel reschedule(final MathContext mathContext, final InterestMethod interestMethod,
            final LoanRescheduleRequest loanRescheduleRequest, final ApplicationCurrency applicationCurrency,
            final HolidayDetailDTO holidayDetailDTO, final CalendarInstance restCalendarInstance,
            final CalendarInstance compoundingCalendarInstance, final Calendar loanCalendar, final FloatingRateDTO floatingRateDTO) {

        LoanRescheduleModel loanRescheduleModel = null;

        switch (interestMethod) {
            case DECLINING_BALANCE:
                loanRescheduleModel = new DecliningBalanceInterestLoanScheduleGenerator().reschedule(mathContext, loanRescheduleRequest,
                        applicationCurrency, holidayDetailDTO, restCalendarInstance, compoundingCalendarInstance, loanCalendar,
                        floatingRateDTO);
            break;

            case FLAT:
                loanRescheduleModel = new FlatInterestLoanScheduleGenerator().reschedule(mathContext, loanRescheduleRequest,
                        applicationCurrency, holidayDetailDTO, restCalendarInstance, compoundingCalendarInstance, loanCalendar,
                        floatingRateDTO);
            break;

            case INVALID:
            break;
        }

        return loanRescheduleModel;
    }
}
