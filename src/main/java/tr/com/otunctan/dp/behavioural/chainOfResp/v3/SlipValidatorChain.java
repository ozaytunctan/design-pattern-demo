package tr.com.otunctan.dp.behavioural.chainOfResp.v3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Slip {
    private Integer journalEntryNo;
    private LocalDateTime journalEntryDate;
    private String description;

    public Integer getJournalEntryNo() {
        return journalEntryNo;
    }

    public void setJournalEntryNo(Integer journalEntryNo) {
        this.journalEntryNo = journalEntryNo;
    }

    public LocalDateTime getJournalEntryDate() {
        return journalEntryDate;
    }

    public void setJournalEntryDate(LocalDateTime journalEntryDate) {
        this.journalEntryDate = journalEntryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

public interface SlipValidatorChain {
    void validate(Slip slip);
}

interface SlipValidator {
    void validate(Slip slip, SlipValidatorChain chain);
}


class CompositeSlipValidator implements SlipValidatorChain {
    private List<SlipValidator> validators = new ArrayList<>();

    public void setValidators(List<SlipValidator> validators) {
        this.validators = validators;
    }

    public void add(SlipValidator validator) {
        this.validators.add(validator);
    }

    @Override
    public void validate(Slip slip) {
        new VirtualSlipValidatorChain(this, validators).validate(slip);
    }

    private static class VirtualSlipValidatorChain implements SlipValidatorChain {
        private final SlipValidatorChain originalChain;
        private final List<? extends SlipValidator> validators;
        private int currentPosition = 0;

        public VirtualSlipValidatorChain(SlipValidatorChain chain, List<? extends SlipValidator> validators) {
            this.originalChain = chain;
            this.validators = validators;
        }

        @Override
        public void validate(Slip slip) {
            if (this.currentPosition == this.validators.size()) {
                return;
            }

            ++this.currentPosition;
            SlipValidator nextValidator = this.validators.get(this.currentPosition - 1);
            nextValidator.validate(slip, this);

        }
    }
}

class NotNullSlipValidator implements SlipValidator {
    public NotNullSlipValidator() {
    }

    @Override
    public void validate(Slip slip, SlipValidatorChain chain) {
        if (Objects.isNull(slip)) {
            throw new IllegalArgumentException("Slip is null");
        }
        chain.validate(slip);
    }
}

// SlipJournalEntryDateValidator validates the journal entry date
class SlipJournalEntryDateValidator implements SlipValidator {
    @Override
    public void validate(Slip slip, SlipValidatorChain chain) {
        LocalDateTime journalEntryDate = slip.getJournalEntryDate();
        if (journalEntryDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("İleri tarihli fiş kayıt edilemez.");
        }
        chain.validate(slip);
    }
}


class Demo {
    public static void main(String[] args) {
        CompositeSlipValidator validator = new CompositeSlipValidator();
        validator.add(new NotNullSlipValidator());
        validator.add(new SlipJournalEntryDateValidator());


        Slip slip = new Slip();
        slip.setJournalEntryDate(LocalDateTime.now());  // Valid date for testing
//        slip.setJournalEntryDate(LocalDateTime.now().plusDays(2));  // Valid date for testing
        slip.setDescription("MIF");
        slip.setJournalEntryNo(5);

        try {
            validator.validate(slip);
            System.out.println("Slip is valid.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
