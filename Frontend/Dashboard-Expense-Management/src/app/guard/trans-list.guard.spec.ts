import { TestBed, async, inject } from '@angular/core/testing';

import { TransListGuard } from './trans-list.guard';

describe('TransListGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TransListGuard]
    });
  });

  it('should ...', inject([TransListGuard], (guard: TransListGuard) => {
    expect(guard).toBeTruthy();
  }));
});
